package xdroid.mwee.com.rxjavatest;

/**
 * Created by zhangmin on 2018/7/30.
 */

@TestAnnotation(id = 3, value = "张敏")
public class Test {

    @TestAnnotation(id = 100, value = "filed")
    public int filed = 10001;


    @TestAnnotation(id = 200, value = "滴滴滴")
    public int filed2 = 20001;


    /*@TestAnnotation(id = 1, value = "world")
    public void hello1() {
        System.out.println("hell1--->  6 / 0=" + 6 / 0);
    }*/

   /* @TestAnnotation(id = 2, value = "world  hell2")
    public void hello2() {
        //System.out.println("hell2--->  6 / 0=" + 6 / 0);
        System.out.println("hell2--->");
    }*/

    @TestAnnotation(id = 3, value = "world  hell3")
    public void hello3(String value) {
        System.out.println("hell3-->  "+value);
    }

    public void hello4(String value) {
        System.out.println(value);
    }

}
