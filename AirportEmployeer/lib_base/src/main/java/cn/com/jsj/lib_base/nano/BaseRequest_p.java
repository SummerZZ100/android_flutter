// Generated by the protocol buffer compiler.  DO NOT EDIT!

package cn.com.jsj.lib_base.nano;

import cn.com.jsj.lib_common.netcore.bean.ParcelableMessageNano;
import cn.com.jsj.lib_common.netcore.bean.ParcelableMessageNanoCreator;

@SuppressWarnings("hiding")
public final class BaseRequest_p extends
        ParcelableMessageNano {

  // Used by Parcelable
  @SuppressWarnings({"unused"})
  public static final Creator<BaseRequest_p> CREATOR =
      new ParcelableMessageNanoCreator<
                BaseRequest_p>(BaseRequest_p.class);

  private static volatile BaseRequest_p[] _emptyArray;
  public static BaseRequest_p[] emptyArray() {
    // Lazily initializes the empty array
    if (_emptyArray == null) {
      synchronized (
          com.google.protobuf.nano.InternalNano.LAZY_INIT_LOCK) {
        if (_emptyArray == null) {
          _emptyArray = new BaseRequest_p[0];
        }
      }
    }
    return _emptyArray;
  }

  // optional string AppVersion = 1;
  public String appVersion;

  // optional .SourceWay SourceWay_p = 2 [default = SourceWayNoSetting];
  public int sourceWayP;

  // optional string TerminalExt = 3;
  public String terminalExt;

  // optional string PlatformToken = 4;
  public String platformToken;

  // optional string PlatformAppId = 5;
  public String platformAppId;

  // optional string JSJID = 6;
  public String jSJID;

  // optional string OpEmployeeName = 7;
  public String opEmployeeName;

  // optional .SourceApp_p SourceApp_p = 8 [default = SourceAppNoSetting_p];
  public int sourceAppP;

  // optional string LoginToken = 9;
  public String loginToken;

  public BaseRequest_p() {
    clear();
  }

  public BaseRequest_p clear() {
    appVersion = "";
    sourceWayP = SourceWay.SourceWayNoSetting;
    terminalExt = "";
    platformToken = "";
    platformAppId = "";
    jSJID = "";
    opEmployeeName = "";
    sourceAppP = SourceApp_p.SourceAppNoSetting_p;
    loginToken = "";
    cachedSize = -1;
    return this;
  }

  @Override
  public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
      throws java.io.IOException {
    if (!this.appVersion.equals("")) {
      output.writeString(1, this.appVersion);
    }
    if (this.sourceWayP != SourceWay.SourceWayNoSetting) {
      output.writeInt32(2, this.sourceWayP);
    }
    if (!this.terminalExt.equals("")) {
      output.writeString(3, this.terminalExt);
    }
    if (!this.platformToken.equals("")) {
      output.writeString(4, this.platformToken);
    }
    if (!this.platformAppId.equals("")) {
      output.writeString(5, this.platformAppId);
    }
    if (!this.jSJID.equals("")) {
      output.writeString(6, this.jSJID);
    }
    if (!this.opEmployeeName.equals("")) {
      output.writeString(7, this.opEmployeeName);
    }
    if (this.sourceAppP != SourceApp_p.SourceAppNoSetting_p) {
      output.writeInt32(8, this.sourceAppP);
    }
    if (!this.loginToken.equals("")) {
      output.writeString(9, this.loginToken);
    }
    super.writeTo(output);
  }

  @Override
  protected int computeSerializedSize() {
    int size = super.computeSerializedSize();
    if (!this.appVersion.equals("")) {
      size += com.google.protobuf.nano.CodedOutputByteBufferNano
          .computeStringSize(1, this.appVersion);
    }
    if (this.sourceWayP != SourceWay.SourceWayNoSetting) {
      size += com.google.protobuf.nano.CodedOutputByteBufferNano
        .computeInt32Size(2, this.sourceWayP);
    }
    if (!this.terminalExt.equals("")) {
      size += com.google.protobuf.nano.CodedOutputByteBufferNano
          .computeStringSize(3, this.terminalExt);
    }
    if (!this.platformToken.equals("")) {
      size += com.google.protobuf.nano.CodedOutputByteBufferNano
          .computeStringSize(4, this.platformToken);
    }
    if (!this.platformAppId.equals("")) {
      size += com.google.protobuf.nano.CodedOutputByteBufferNano
          .computeStringSize(5, this.platformAppId);
    }
    if (!this.jSJID.equals("")) {
      size += com.google.protobuf.nano.CodedOutputByteBufferNano
          .computeStringSize(6, this.jSJID);
    }
    if (!this.opEmployeeName.equals("")) {
      size += com.google.protobuf.nano.CodedOutputByteBufferNano
          .computeStringSize(7, this.opEmployeeName);
    }
    if (this.sourceAppP != SourceApp_p.SourceAppNoSetting_p) {
      size += com.google.protobuf.nano.CodedOutputByteBufferNano
        .computeInt32Size(8, this.sourceAppP);
    }
    if (!this.loginToken.equals("")) {
      size += com.google.protobuf.nano.CodedOutputByteBufferNano
          .computeStringSize(9, this.loginToken);
    }
    return size;
  }

  @Override
  public BaseRequest_p mergeFrom(
          com.google.protobuf.nano.CodedInputByteBufferNano input)
      throws java.io.IOException {
    while (true) {
      int tag = input.readTag();
      switch (tag) {
        case 0:
          return this;
        default: {
          if (!com.google.protobuf.nano.WireFormatNano.parseUnknownField(input, tag)) {
            return this;
          }
          break;
        }
        case 10: {
          this.appVersion = input.readString();
          break;
        }
        case 16: {
          int value = input.readInt32();
          switch (value) {
            case SourceWay.SourceWayNoSetting:
            case SourceWay.Web:
            case SourceWay.Wap:
            case SourceWay.IOS:
            case SourceWay.Android:
            case SourceWay.CRM_p:
            case SourceWay.BusinessTravel:
            case SourceWay.WeChat:
            case SourceWay.WeChatProgram:
              this.sourceWayP = value;
              break;
          }
          break;
        }
        case 26: {
          this.terminalExt = input.readString();
          break;
        }
        case 34: {
          this.platformToken = input.readString();
          break;
        }
        case 42: {
          this.platformAppId = input.readString();
          break;
        }
        case 50: {
          this.jSJID = input.readString();
          break;
        }
        case 58: {
          this.opEmployeeName = input.readString();
          break;
        }
        case 64: {
          int value = input.readInt32();
          switch (value) {
            case SourceApp_p.SourceAppNoSetting_p:
            case SourceApp_p.GoldenCentury_p:
            case SourceApp_p.AirwayKeeper_p:
            case SourceApp_p.TrainTicket_p:
              this.sourceAppP = value;
              break;
          }
          break;
        }
        case 74: {
          this.loginToken = input.readString();
          break;
        }
      }
    }
  }

  public static BaseRequest_p parseFrom(byte[] data)
      throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
    return com.google.protobuf.nano.MessageNano.mergeFrom(new BaseRequest_p(), data);
  }

  public static BaseRequest_p parseFrom(
          com.google.protobuf.nano.CodedInputByteBufferNano input)
      throws java.io.IOException {
    return new BaseRequest_p().mergeFrom(input);
  }
}