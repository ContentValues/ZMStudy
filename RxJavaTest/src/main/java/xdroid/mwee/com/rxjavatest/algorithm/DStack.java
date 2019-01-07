package xdroid.mwee.com.rxjavatest.algorithm;
import java.util.Stack;

/**
 * Created by zhangmin on 2018/12/14.
 */

public class DStack {

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(1);
        stack.add(1);
        stack.add(2);
        stack.add(2);
        stack.add(3);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

       /* BigDecimal a = new BigDecimal(10);
        BigDecimal b = new BigDecimal(3);
        System.out.println(a.divide(b,2,BigDecimal.ROUND_HALF_UP));*/


    }

    /*static class Note<T> {

        T t;
        Note<T> next;

        public Note(T t, Note<T> next) {
            this.t = t;
            this.next = next;
        }
    }


    static class DStack_1<T> {

        Note<T> top;

        public void push(T e) {

            if (top != null) {
                Note<T> note = new Note<>(e, top);
                top = note;
            }
        }

        public Note<T> pop() {

            if (top == null) {
                return null;
            } else {
                Note<T> temp = top.next;
                top = temp;
                return top;
            }
        }


        public Note<T> peek() {

        }

    }*/


}
