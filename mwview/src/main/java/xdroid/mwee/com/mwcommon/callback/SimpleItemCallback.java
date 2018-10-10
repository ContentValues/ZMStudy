package xdroid.mwee.com.mwcommon.callback;

public abstract class SimpleItemCallback<T, H> extends ItemCallback<T> {
    public void onItemClick(int position, T model, int tag, H holder) {
    }

    public void onItemLongClick(int position, T model, int tag, H holder) {
    }
}
