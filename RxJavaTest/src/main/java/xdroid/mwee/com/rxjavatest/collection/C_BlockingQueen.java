package xdroid.mwee.com.rxjavatest.collection;


import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangmin on 2019/4/3.
 */

public class C_BlockingQueen {

    public static void main(String[] args) throws InterruptedException {

        CT_BlockingQueen ct_blockingQueen = new CT_BlockingQueen(3);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    ct_blockingQueen.put(1);
                    ct_blockingQueen.put(2);
                    ct_blockingQueen.put(3);
                    ct_blockingQueen.put(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        Thread.sleep(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    ct_blockingQueen.take();
                    ct_blockingQueen.take();
                    ct_blockingQueen.take();
                    ct_blockingQueen.take();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        Thread.sleep(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    ct_blockingQueen.put(5);
                    ct_blockingQueen.put(6);
                    ct_blockingQueen.put(7);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }


    static class CT_BlockingQueen implements BlockingQueue<Integer> {

        /**
         * The queued items
         */
        Integer[] items;

        /**
         * items index for next take, poll, peek or remove
         */
        int takeIndex;

        /**
         * items index for next put, offer, or add
         */
        int putIndex;

        /**
         * Number of elements in the queue
         */
        int count;

        /**
         * Main lock guarding all access
         */
        ReentrantLock lock;

        /**
         * Condition for waiting takes
         */
        Condition notEmpty;

        /**
         * Condition for waiting puts
         */
        Condition notFull;


        public CT_BlockingQueen(int capacity) {
            this.items = new Integer[capacity];
            lock = new ReentrantLock(false);
            notEmpty = lock.newCondition();
            notFull = lock.newCondition();
        }


        private void enqueue(Integer x) {

            items[putIndex] = x;

            if (++putIndex == items.length) {
                putIndex = 0;
            }
            count++;
            notEmpty.signal();

            System.out.println("放入成功" + x);

        }


        private Integer dequeue() {
            Integer x = items[takeIndex];
            if (++takeIndex == items.length) takeIndex = 0;
            count--;
            notFull.signal();

            System.out.println("取出成功" + x);
            return x;

        }


        @Override
        public boolean add(Integer e) {
            offer(e);
            return false;
        }

        @Override
        public boolean offer(Integer integer) {
            Objects.requireNonNull(integer);
            ReentrantLock lock = this.lock;
            lock.lock();

            try {
                if (count == items.length) {
                    return false;
                } else {
                    enqueue(integer);
                }
            } finally {
                lock.unlock();
            }
            return false;
        }

        @Override
        public void put(Integer integer) throws InterruptedException {
            Objects.requireNonNull(integer);
            ReentrantLock lock = this.lock;
            lock.lock();
            try {
                while (count == items.length) {
                    System.out.println("集合已满   线程正在阻塞 停止放入数据-->" + integer);
                    notFull.await();
                    System.out.println("集合已满   但是被唤醒了 开始放入数据-->" + integer);
                }
                enqueue(integer);
            } finally {
                lock.unlock();
            }
        }


        @Override
        public Integer take() throws InterruptedException {

            ReentrantLock lock = this.lock;
            lock.lock();
            try {
                while (count == 0) {
                    System.out.println("集合已空   线程正在阻塞 停止取数据");
                    notEmpty.await();
                    System.out.println("集合已空   但是被唤醒了 开始取数据");
                }
                return dequeue();
            } finally {
                lock.unlock();
            }
        }


        @Override
        public Integer remove() {
            return null;
        }

        @Override
        public Integer poll() {

            ReentrantLock lock = this.lock;
            lock.lock();
            try {
                return count == 0 ? null : dequeue();
            } finally {
                lock.unlock();
            }
        }

        @Override
        public Integer element() {
            return null;
        }

        @Override
        public Integer peek() {
            return null;
        }


        @Override
        public boolean offer(Integer integer, long l, TimeUnit timeUnit) throws InterruptedException {
            return false;
        }


        @Override
        public Integer poll(long l, TimeUnit timeUnit) throws InterruptedException {
            return null;
        }

        @Override
        public int remainingCapacity() {
            return 0;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Integer> collection) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Integer> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] ts) {
            return null;
        }

        @Override
        public int drainTo(Collection<? super Integer> collection) {
            return 0;
        }

        @Override
        public int drainTo(Collection<? super Integer> collection, int i) {
            return 0;
        }
    }


}
