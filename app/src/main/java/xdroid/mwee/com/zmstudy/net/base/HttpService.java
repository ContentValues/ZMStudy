package xdroid.mwee.com.zmstudy.net.base;
import com.mwee.android.sqlite.base.APPConfig;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;
import xdroid.mwee.com.zmstudy.model.BindResponse;
import xdroid.mwee.com.zmstudy.model.bean.GankModel;
import xdroid.mwee.com.zmstudy.model.GetDataResponse;
import xdroid.mwee.com.zmstudy.model.bean.KBTempDataResponse;

/**
 * 测试接口service-post相关
 * Created by WZG on 2016/12/19.
 */

public interface HttpService {

    String BASE_URL = "http://gank.io/api/";

    String MWEE_BASE_URL = "http://pcdc.winpos.9now.net/posapi/shop/" +
            APPConfig.SHOP_ID +
            "-103/";


    /**
     * 获取页面数据
     *
     * @param type
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GET("data/{type}/{number}/{page}")
    Observable<GankModel> getGankData(@Path("type") String type,
                                      @Path("number") int pageSize,
                                      @Path("page") int pageNum);


    /**
     * @return
     */
    @POST("kborder/getList")
    Observable<KBTempDataResponse> queryKBOrderList(@Body RequestBody requestBody);


    @FormUrlEncoded
    @POST("kborder/getList")
    Observable<KBTempDataResponse> queryKBOrderList (@Field("pageNo") String pageNo
            , @Field("pageSize") String pageSize
            , @Field("queryType") String queryType);

    /**
     * 绑定接口
     *
     * @return
     */
    /*@FormUrlEncoded*/
    @POST("shopapi/bindingWithShopType")
    Observable<BindResponse> bindShop(@Body RequestBody requestBody);

    /**
     * 下载接口
     *
     * @return
     */
   /* @FormUrlEncoded*/
    @POST("shopapi/download")
    Observable<GetDataResponse> downLoad(@Body RequestBody requestBody);

}
