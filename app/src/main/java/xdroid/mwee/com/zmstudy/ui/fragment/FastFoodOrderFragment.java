package xdroid.mwee.com.zmstudy.ui.fragment;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBSimpleUtil;

import java.util.List;

import com.mwee.android.tools.ButtonClickTimer;

import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.mwcommon.callback.ResultCallback;
import xdroid.mwee.com.mwcommon.callback.SimpleCallback;
import xdroid.mwee.com.mwcommon.xrecyclerview.XRecyclerView;
import xdroid.mwee.com.mwcommon.xrecyclerview.divider.HorizontalDividerItemDecoration;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.ui.adapter.FastFoodOrderApapter;
import xdroid.mwee.com.zmstudy.model.menu.MenuItem;
import xdroid.mwee.com.zmstudy.model.order.OrderCache;
import xdroid.mwee.com.zmstudy.business.processor.BusDataProcessor;

/**
 * Created by zhangmin on 2018/6/29.
 */

public class FastFoodOrderFragment extends BaseFragment implements View.OnClickListener {

    private FastFoodOrderApapter fastFoodOrderApapter;
    private OrderCache orderCache;
    private XRecyclerView mMenuOrderRecyclerView;

    public static FastFoodOrderFragment newInstance() {
        return new FastFoodOrderFragment();
    }

    @Override
    public void initView(View v) {

        mMenuOrderRecyclerView = v.findViewById(R.id.mMenuOrderRecyclerView);
        v.findViewById(R.id.tvOrderToCenter).setOnClickListener(this);
        v.findViewById(R.id.tvPackOrder).setOnClickListener(this);
        v.findViewById(R.id.tvPrinterPre).setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        mMenuOrderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMenuOrderRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).margin(1).color(R.color.colorPrimary).build());
        fastFoodOrderApapter = new FastFoodOrderApapter(getContext());
        mMenuOrderRecyclerView.setAdapter(fastFoodOrderApapter);

        MenuFragment menuFragment = MenuFragment.newInstance();
        menuFragment.setOnMenuItemClickListener(new SimpleCallback<MenuItem>() {
            @Override
            public void action(MenuItem data) {

                data.init(orderCache.currentSeq);
                orderCache.tempMenuList.add(data);

                fastFoodOrderApapter.setOrderCache(orderCache);
            }
        });
        getFragmentManager().beginTransaction().replace(R.id.mMenuFragment, menuFragment).commitAllowingStateLoss();

        BusDataProcessor.getInstance().loadOpenOrder(new ResultCallback<OrderCache>() {
            @Override
            public void onSuccess(OrderCache data) {
                orderCache = data;
                fastFoodOrderApapter.setOrderCache(orderCache);
            }
        });


    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_food_order;
    }

    @Override
    public void onClick(View v) {
        if (!ButtonClickTimer.canClick()) {
            return;
        }
        switch (v.getId()) {
            case R.id.tvOrderToCenter:
                doOrderToCenter();
                break;
            case R.id.tvPackOrder:
                doPackOrder();
                break;
            case R.id.tvPrinterPre:
                //PrintBillUtil.printPreBill(orderCache);
                break;
            default:
                break;
        }
    }


    /**
     * 点击了下单
     */
    private void doOrderToCenter() {
        BusDataProcessor.getInstance().loadOrderToCenter(orderCache, true, new ResultCallback<OrderCache>() {
            @Override
            public void onSuccess(OrderCache data) {
                orderCache = data;
                fastFoodOrderApapter.setOrderCache(orderCache);
            }
        });
    }


    /**
     * 点击了取单
     */
    private void doPackOrder() {
        List<String> stringList = DBSimpleUtil.queryStringList(APPConfig.DB_MAIN, "select order_id from order_cache where order_status = '1'");
        String[] itemS = new String[stringList.size()];
        final int[] choicePosition = new int[1];
        int checkedItem = 0;
        if (!TextUtils.isEmpty(orderCache.orderID)) {
            checkedItem = stringList.indexOf(orderCache.orderID);
        }
        AlertDialog dialog = new AlertDialog.Builder(getContext()).setTitle("请选择订单号:")
                .setSingleChoiceItems(stringList.toArray(itemS), checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choicePosition[0] = which;

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BusDataProcessor.getInstance().loadPackOrder(itemS[choicePosition[0]], new ResultCallback<OrderCache>() {
                            @Override
                            public void onSuccess(OrderCache data) {
                                orderCache = data;
                                fastFoodOrderApapter.setOrderCache(orderCache);
                            }
                        });
                        dialog.dismiss();
                    }
                }).create();

        dialog.show();

    }


}
