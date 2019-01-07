package xdroid.mwee.com.mwbase;

public class NetError extends Exception {
    public Throwable exception;
    public int errorNo = NoConnectError;
    public String errorMsg = "";

    public static final int ParseError = 0;   //数据解析异常
    public static final int NoConnectError = 1;   //无连接异常
    public static final int AuthError = 2;   //用户验证异常
    public static final int NoDataError = 3;   //无数据返回异常
    public static final int BusinessError = 4;   //业务异常
    public static final int OtherError = 5;   //其他异常


    public NetError(Throwable exception, int errorNo,String errorMsg) {
        this.exception = exception;
        this.errorNo = errorNo;
        this.errorMsg = errorMsg;
    }


    public NetError( int errorNo,String errorMsg) {
        this.errorNo = errorNo;
        this.errorMsg = errorMsg;
    }


   /* public NetError(Throwable exception, int type) {
        this.exception = exception;
        this.type = type;
    }

    public NetError(String detailMessage, int type) {
        super(detailMessage);
        this.type = type;
    }

    @Override
    public String getMessage() {
        if (exception != null) return exception.getMessage();
        return super.getMessage();
    }

    public int getType() {
        return type;
    }*/
}
