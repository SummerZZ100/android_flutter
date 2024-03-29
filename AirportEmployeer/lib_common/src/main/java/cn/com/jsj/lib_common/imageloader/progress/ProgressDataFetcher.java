package cn.com.jsj.lib_common.imageloader.progress;

import androidx.annotation.NonNull;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 */
public class ProgressDataFetcher implements DataFetcher<InputStream> {

    private static final String TAG = "ProgressDataFetcher";

    private final String url;
    private Call progressCall;
    private InputStream stream;
    private volatile boolean isCancelled;
    private ProgressUIListener proListener;

    public ProgressDataFetcher(String url, ProgressUIListener listener) {
        this.url = url;
        this.proListener = listener;
    }


    @Override
    public void loadData(@NonNull Priority priority, @NonNull DataCallback<? super InputStream> callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        final ProgressListener progressListener = new ProgressListener() {

            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                if (proListener != null) {
                    proListener.update((int) bytesRead, (int) contentLength);
                }
            }
        };
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {

                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response originalResponse = chain.proceed(chain.request());
                        return originalResponse.newBuilder()
                                .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                                .build();
                    }
                })
                .build();
        try {
            progressCall = client.newCall(request);
            Response response = progressCall.execute();
            if (isCancelled) {
                return;
            }
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            stream = response.body().byteStream();
            callback.onDataReady(stream);
        } catch (IOException e) {
            e.printStackTrace();
            callback.onLoadFailed(e);
        }
    }

    @Override
    public void cleanup() {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                // Ignore
            }
        }
        if (progressCall != null) {
            progressCall.cancel();
        }
    }


    @Override
    public void cancel() {
        // TODO: we should consider disconnecting the url connection here, but we can't do so directly because cancel is
        // often called on the main thread.
        isCancelled = true;
    }

    @NonNull
    @Override
    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }

    @NonNull
    @Override
    public DataSource getDataSource() {
        return DataSource.REMOTE;
    }
}
