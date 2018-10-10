package xdroid.mwee.com.zmstudy;

import com.alibaba.fastjson.JSON;

import org.junit.Test;

import java.util.ArrayList;

import xdroid.mwee.com.zmstudy.cute.Thread.AutomicIntegerTest;
import xdroid.mwee.com.zmstudy.cute.Thread.ThreadDemo;
import xdroid.mwee.com.zmstudy.cute.Thread.URLDemo;
import xdroid.mwee.com.zmstudy.cute.collection.CollectionDemo;
import xdroid.mwee.com.zmstudy.cute.collection.MapDemo;
import xdroid.mwee.com.zmstudy.cute.generic.GenericDemo;
import xdroid.mwee.com.zmstudy.cute.rxjava.CustomerObservable;
import xdroid.mwee.com.zmstudy.cute.rxjava.CustomerObserver;
import xdroid.mwee.com.zmstudy.cute.rxjava.CustomerObserver2;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitGeneric {
    @Test
    public void addition_isCorrect() throws Exception {
        //assertEquals(4, 2 + 2);
        GenericDemo demo = new GenericDemo();
        demo.testGenericClass("张敏","好爽");
        ArrayList arrayList = new ArrayList<String>();
        arrayList.add("小婊砸干死你1");
        arrayList.add("小婊砸干死你2");
        arrayList.add("小婊砸干死你3");
        arrayList.add("小婊砸干死你4");
        demo.testGenericClass(JSON.toJSON(arrayList), "好爽");
    }


    @Test
    public void addition_isCorrect2() throws Exception {
        //assertEquals(4, 2 + 2);
        GenericDemo demo = new GenericDemo();
        System.out.printf( "%d, %d 和 %d 中最大的数为 %d\n\n",
                3, 4, 5, demo.testMaximum( 3, 4, 5 ) );

        System.out.printf( "%.1f, %.1f 和 %.1f 中最大的数为 %.1f\n\n",
                6.6, 8.8, 7.7, demo.testMaximum( 6.6, 8.8, 7.7 ) );

        System.out.printf( "%s, %s 和 %s 中最大的数为 %s\n","pear",
                "apple", "orange", demo.testMaximum( "pear", "apple", "orange" ) );
    }


    @Test
    public void addition_isCorrect3() throws Exception {
        //assertEquals(4, 2 + 2);
        CollectionDemo demo = new CollectionDemo();
        //demo.testCollection();
        demo.testCollection2();
        //demo.testLinkList();
    }


    @Test
    public void addition_isCorrect4() throws Exception {

        CustomerObservable observable = new CustomerObservable();
        CustomerObserver customerObserver = new CustomerObserver();
        observable.addObserver(customerObserver);
        observable.addObserver(customerObserver);
        observable.addObserver(customerObserver);
        observable.addObserver(new CustomerObserver2());
        observable.setData(1);
        observable.setData(2);
        observable.setData(3);
    }


    @Test
    public void addition_isCorrect5() throws Exception {

        MapDemo mapDemo = new MapDemo();
        mapDemo.testHashMap();
        mapDemo.testTreeMap();
    }


    @Test
    public void addition_isCorrect6() throws Exception {

        MapDemo mapDemo = new MapDemo();
        mapDemo.testIterator();
    }

    @Test
    public void addition_isCorrect7() throws Exception {

        MapDemo mapDemo = new MapDemo();
        mapDemo.testIterator2();
    }

    @Test
    public void addition_isCorrect8() throws Exception {

        MapDemo mapDemo = new MapDemo();
        mapDemo.testCollection();
    }

    @Test
    public void addition_isCorrect9() throws Exception {

        MapDemo mapDemo = new MapDemo();
        mapDemo.testLinkList();
    }


    @Test
    public void addition_isCorrect10() throws Exception {

        MapDemo mapDemo = new MapDemo();
        mapDemo.testSystem();
    }


    @Test
    public void addition_isCorrect11() throws Exception {

        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.testThread1();
    }


    @Test
    public void addition_isCorrect12() throws Exception {

        CollectionDemo collectionDemo = new CollectionDemo();
        CollectionDemo collectionDemo1 = new CollectionDemo();
        CollectionDemo collectionDemo2 = new CollectionDemo();
        System.out.println("测试结果  collectionDemo.a--->" +collectionDemo.a);

        System.out.println("测试结果  collectionDemo.c--->" +collectionDemo.c);
        System.out.println("测试结果  collectionDemo1.c1--->" +collectionDemo1.c);
        System.out.println("测试结果  collectionDemo2.c2--->" +collectionDemo2.c);


        System.out.println("测试结果  collectionDemo.c--->" +collectionDemo.c--);
        System.out.println("测试结果  collectionDemo1.c1--->" +collectionDemo1.c);
        System.out.println("测试结果  collectionDemo2.c2--->" +collectionDemo2.c);

        System.out.println("测试结果  collectionDemo.c--->" +--collectionDemo.c);
        System.out.println("测试结果  collectionDemo1.c1--->" +collectionDemo1.c);
        System.out.println("测试结果  collectionDemo2.c2--->" +collectionDemo2.c);
    }


    @Test
    public void addition_isCorrect13() throws Exception {

        URLDemo urlDemo = new URLDemo();
        urlDemo.testURL();
    }

    @Test
    public void addition_isCorrect14() throws Exception {

        AutomicIntegerTest automicIntegerTest = new AutomicIntegerTest();
        automicIntegerTest.test();
    }


    @Test
    public void addition_isCorrect15() throws Exception {

        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.testThread3();
    }

}