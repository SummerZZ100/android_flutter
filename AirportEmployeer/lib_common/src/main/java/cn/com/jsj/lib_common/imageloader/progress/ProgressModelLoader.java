package cn.com.jsj.lib_common.imageloader.progress;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;

import java.io.InputStream;

/**
 *
 */
public class ProgressModelLoader implements ModelLoader<String, InputStream> {

    private final ModelCache<String, String> modelCache;
    private ProgressUIListener proListener;

    public ProgressModelLoader(ProgressUIListener listener) {
        this(null, listener);
    }

    public ProgressModelLoader(ModelCache<String, String> modelCache) {
        this(modelCache, null);
    }


    public ProgressModelLoader(ModelCache<String, String> modelCache, ProgressUIListener listener) {
        this.modelCache = modelCache;
        this.proListener = listener;
    }


    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(@NonNull String s, int width, int height, @NonNull Options options) {
        String result = null;
        if (modelCache != null) {
            result = modelCache.get(s, width, height);
        }
        if (result == null) {
            result = s;
            if (modelCache != null) {
                modelCache.put(s, width, height, result);
            }
        }
        return new LoadData<>(new ObjectKey(s), new ProgressDataFetcher(s, proListener));
    }

    @Override
    public boolean handles(@NonNull String s) {
        return true;
    }


}
