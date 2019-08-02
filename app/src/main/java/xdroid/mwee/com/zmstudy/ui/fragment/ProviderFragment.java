package xdroid.mwee.com.zmstudy.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.zmstudy.R;

public class ProviderFragment extends BaseFragment {


    public static ProviderFragment newInstance() {
        return new ProviderFragment();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_multi_type;
    }

    @Override
    public void initView(View v) {

        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        ProviderAdapter providerAdapter = new ProviderAdapter();

        for (int i = 0; i < 10; i++) {
            providerAdapter.modules.add(String.valueOf(i));
        }
        recyclerView.setAdapter(providerAdapter);

        providerAdapter.notifyDataSetChanged();

    }


    class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.ProviderViewHolder> {

        public List<String> modules = new ArrayList<>();


        @NonNull
        @Override
        public ProviderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            ProviderViewHolder holder = new ProviderViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.type, viewGroup, false));

            Log.d(TAG, "onCreateViewHolder"  + " viewHolder->" + holder.hashCode());
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ProviderViewHolder holder, int positon) {

            Log.i(TAG, "onBindViewHolder"  + "\tpositon->" + positon + "  viewHolder->" + holder.hashCode());

            holder.tvText.setText(modules.get(positon));

        }

        @Override
        public int getItemCount() {
            return modules.size();
        }

        class ProviderViewHolder extends RecyclerView.ViewHolder {
            private TextView tvText;

            public ProviderViewHolder(@NonNull View itemView) {
                super(itemView);
                tvText = itemView.findViewById(R.id.tvText);
            }
        }
    }

}
