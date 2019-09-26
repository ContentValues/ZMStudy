package xdroid.mwee.com.zmstudy.ui.adapter;

import android.database.Observable;
import android.support.annotation.Nullable;

/**
 * Author：created by SugarT
 * Time：2019/9/23 14
 */
public class CC {

    /**
     * 观察者 为啥不定义成抽象接口类呢？？？？
     */
    public abstract static class AdapterDataObserver {
        public void onChanged() {
            // Do nothing
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
            // do nothing
        }

        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            // fallback to onItemRangeChanged(positionStart, itemCount) if app
            // does not override this method.
            onItemRangeChanged(positionStart, itemCount);
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            // do nothing
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            // do nothing
        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            // do nothing
        }
    }

    /**
     * 观察者
     */
    private class RecyclerViewDataObserver extends AdapterDataObserver {
        RecyclerViewDataObserver() {
        }

        @Override
        public void onChanged() {
//            assertNotInLayoutOrScroll(null);
//            mState.mStructureChanged = true;
//
//            processDataSetCompletelyChanged(true);
//            if (!mAdapterHelper.hasPendingUpdates()) {
//                requestLayout();
//            }
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
//            assertNotInLayoutOrScroll(null);
//            if (mAdapterHelper.onItemRangeChanged(positionStart, itemCount, payload)) {
//                triggerUpdateProcessor();
//            }
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
//            assertNotInLayoutOrScroll(null);
//            if (mAdapterHelper.onItemRangeInserted(positionStart, itemCount)) {
//                triggerUpdateProcessor();
//            }
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
//            assertNotInLayoutOrScroll(null);
//            if (mAdapterHelper.onItemRangeRemoved(positionStart, itemCount)) {
//                triggerUpdateProcessor();
//            }
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
//            assertNotInLayoutOrScroll(null);
//            if (mAdapterHelper.onItemRangeMoved(fromPosition, toPosition, itemCount)) {
//                triggerUpdateProcessor();
//            }
        }

        void triggerUpdateProcessor() {
//            if (POST_UPDATES_ON_ANIMATION && mHasFixedSize && mIsAttached) {
//                ViewCompat.postOnAnimation(RecyclerView.this, mUpdateChildViewsRunnable);
//            } else {
//                mAdapterUpdateDuringMeasure = true;
//                requestLayout();
//            }
        }
    }

    /**
     * 被观察者
     */
    static class AdapterDataObservable extends Observable<AdapterDataObserver> {
        public boolean hasObservers() {
            return !mObservers.isEmpty();
        }

        public void notifyChanged() {
            // since onChanged() is implemented by the app, it could do anything, including
            // removing itself from {@link mObservers} - and that could cause problems if
            // an iterator is used on the ArrayList {@link mObservers}.
            // to avoid such problems, just march thru the list in the reverse order.
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onChanged();
            }
        }

        public void notifyItemRangeChanged(int positionStart, int itemCount) {
            notifyItemRangeChanged(positionStart, itemCount, null);
        }

        public void notifyItemRangeChanged(int positionStart, int itemCount,
                                           @Nullable Object payload) {
            // since onItemRangeChanged() is implemented by the app, it could do anything, including
            // removing itself from {@link mObservers} - and that could cause problems if
            // an iterator is used on the ArrayList {@link mObservers}.
            // to avoid such problems, just march thru the list in the reverse order.
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onItemRangeChanged(positionStart, itemCount, payload);
            }
        }

        public void notifyItemRangeInserted(int positionStart, int itemCount) {
            // since onItemRangeInserted() is implemented by the app, it could do anything,
            // including removing itself from {@link mObservers} - and that could cause problems if
            // an iterator is used on the ArrayList {@link mObservers}.
            // to avoid such problems, just march thru the list in the reverse order.
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onItemRangeInserted(positionStart, itemCount);
            }
        }

        public void notifyItemRangeRemoved(int positionStart, int itemCount) {
            // since onItemRangeRemoved() is implemented by the app, it could do anything, including
            // removing itself from {@link mObservers} - and that could cause problems if
            // an iterator is used on the ArrayList {@link mObservers}.
            // to avoid such problems, just march thru the list in the reverse order.
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onItemRangeRemoved(positionStart, itemCount);
            }
        }

        public void notifyItemMoved(int fromPosition, int toPosition) {
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onItemRangeMoved(fromPosition, toPosition, 1);
            }
        }
    }



}
