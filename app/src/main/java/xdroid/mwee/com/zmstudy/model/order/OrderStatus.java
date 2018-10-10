package xdroid.mwee.com.zmstudy.model.order;

/**
 * OrderStatus
 * Created by virgil on 16/6/20.
 */
public class OrderStatus {
    public final static int CREATED = 0;//    待生成
    public final static int NORMAL = 1;//    未結帳(一般、挂单)

    public final static int NORMAL_SENT = 2;//未結帳外送派出
    public final static int PAIED = 3;//已結帳
    public final static int ANTI_PAIED = 4;//反結帳
    public final static int CANCEL = 5;//取消
    public final static int ANTI_BACK = 6;//反结备份

}
