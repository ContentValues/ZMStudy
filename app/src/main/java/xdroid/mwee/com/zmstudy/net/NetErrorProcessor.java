package xdroid.mwee.com.zmstudy.net;

/**
 * Created by zhangmin on 2018/4/11.
 */

public class NetErrorProcessor {


    public static String queryErrorMsg(NetError error) {
        String errorMsg = "";
        if (error != null) {
            switch (error.getType()) {
                case NetError.ParseError:
                    errorMsg = "数据解析异常";
                    break;

                case NetError.AuthError:
                    errorMsg = "身份验证异常";
                    break;

                case NetError.BusinessError:
                    errorMsg = "业务异常";
                    break;

                case NetError.NoConnectError:
                    errorMsg = "网络无连接";
                    break;

                case NetError.NoDataError:
                    errorMsg = "数据为空";
                    break;

                case NetError.OtherError:
                    errorMsg = "其他异常";
                    break;
            }
        }
        return errorMsg;
    }
}
