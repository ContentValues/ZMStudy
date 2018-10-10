package xdroid.mwee.com.zmstudy.model.order;


import com.mwee.android.tools.base.BusinessBean;

/**
 * 单序的Model
 * Created by virgil on 2016/10/25.
 */
public class OrderSeqModel extends BusinessBean {
    /**
     * 单序
     */
    public int seqNo = 0;
    /**
     * 单序状态
     * see{@link OrderSeqStatus}
     */
    public int seqStatus = 0;
    /**
     * 单序创建时间
     */
    public String createTime = "";
    /**
     * 单序的服务员
     */
    public String createWaiterID = "";
    /**
     * 单序的服务员名称
     */
    public String createWaiterName = "";
    /**
     * 单序的站点
     */
    public String createHostID="";


    public OrderSeqModel() {

    }
}
