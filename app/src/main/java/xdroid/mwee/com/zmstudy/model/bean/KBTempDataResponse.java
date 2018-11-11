package xdroid.mwee.com.zmstudy.model.bean;


import com.mwee.android.tools.base.BaseResponse;
import com.mwee.android.tools.base.BusinessBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmin on 2018/5/25.
 */

public class KBTempDataResponse extends BaseResponse {

    public KBTempDataModel data = new KBTempDataModel();


    static class KBTempDataModel extends BusinessBean {

        public List<KBTempDataBean> koubeiOrder = new ArrayList<>();

        public int pageCount;

        public int count;

        /**
         * 是否有用户申请退款的订单
         */
        public boolean ifPayStatus;
    }

}
