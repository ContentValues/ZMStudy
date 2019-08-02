package xdroid.mwee.com.zmstudy.ui.xdview;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.Collections;
import java.util.List;


/**
 * Created by zhangmin on 2019/4/12.
 */

public class DelegateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = DelegateAdapter.class.getSimpleName();

    private List<Object> datas;

    private ItemViewTypes itemViewTypes;

    //private DelegateAdapter.Builder builder;


    public DelegateAdapter(Builder builder) {
        datas = Collections.emptyList();
        //this.builder = builder;
        this.itemViewTypes = builder.itemViewTypes;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }


    @Override
    public int getItemViewType(int position) {
        //获取储存Item类型class集合的索引
        int index = this.itemViewTypes.getClassIndexOf(datas.get(position).getClass());
        return index;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        VHolder<?, ?> vHolder = itemViewTypes.getItemView(viewType);
        return vHolder.onCreateViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder 11111");
        VHolder vHolder = itemViewTypes.getItemView(holder.getItemViewType());
        vHolder.onBindViewHolder(holder, datas.get(position));
//        if (null != mOnItemClickListener) {
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemClickListener.onItemClick(v, position, datas.get(position));
//                }
//            });
//        }
//        if (null != mOnItemLongClickListener) {
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    mOnItemLongClickListener.onItemLongClick(v, position, datas.get(position));
//                    return true;
//                }
//            });
//        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public static class Builder<T> {
        /**
         * item类型容器
         */
        private ItemViewTypes itemViewTypes;

//        private Class<? extends T> clazz;
//
//        private VHolder<T, ?>[] vHolders;

//        private OnItemLongClickListener mOnItemLongClickListener;
//
//        private OnItemClickListener mOnItemClickListener;

        public Builder() {
            itemViewTypes = new ItemViewTypes();
        }

        /**
         * 数据类型一对一
         *
         * @param clazz
         * @param vHolder
         * @return
         */
        public Builder bind(Class<? extends T> clazz, VHolder vHolder) {
            this.itemViewTypes.save(clazz, vHolder);
            return this;
        }

        /**
         * 数据类型一对多
         *
         * @param
         * @param
         * @return
         */
//        public Builder bindArray(Class<? extends T> clazz, VHolder... vHolders) {
//            this.clazz = clazz;
//            this.vHolders = vHolders;
//            return this;
//        }

//        public Builder withClass(OneToMany<T> oneToMany) {
//            Chain<T> chain = new ChainOneToMany(oneToMany, vHolders);
//            for (VHolder itemView : vHolders) {
//                viewTypes.save(this.clazz, itemView, chain);
//            }
//            return this;
//        }
//
//        public Builder setOnItemClickListener(OnItemClickListener onItemClickListener) {
//            this.mOnItemClickListener = onItemClickListener;
//            return this;
//        }
//
//        public Builder setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
//            this.mOnItemLongClickListener = onItemLongClickListener;
//            return this;
//        }

        public DelegateAdapter build() {
            return new DelegateAdapter(this);
        }

    }
}
