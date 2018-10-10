package xdroid.mwee.com.zmstudy;

import org.junit.Test;

import xdroid.mwee.com.zmstudy.cute.rxjava.RxjavaObservalbeDemo;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        //assertEquals(4, 2 + 2);
        /*RxjavaObservalbeDemo demo = new RxjavaObservalbeDemo();
        demo.observableTest1();*/
        String a1 = "你好";
        System.out.println("你好".equals(a1));
    }


    @Test
    public void addition_isCorrect2() throws Exception {
        //assertEquals(4, 2 + 2);
        RxjavaObservalbeDemo demo = new RxjavaObservalbeDemo();
        demo.observableTest2();
    }


    @Test
    public void addition_isCorrect3() throws Exception {
        //assertEquals(4, 2 + 2);
        RxjavaObservalbeDemo demo = new RxjavaObservalbeDemo();
        demo.flatTest();
    }

    @Test
    public void addition_isCorrect4() throws Exception {
        //assertEquals(4, 2 + 2);
        RxjavaObservalbeDemo demo = new RxjavaObservalbeDemo();
        demo.observableTimer();
    }

    @Test
    public void addition_isCorrect5() throws Exception {
        //assertEquals(4, 2 + 2);
        RxjavaObservalbeDemo demo = new RxjavaObservalbeDemo();
        demo.observableEmpty();
    }



}