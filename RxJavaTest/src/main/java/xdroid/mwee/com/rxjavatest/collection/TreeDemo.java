package xdroid.mwee.com.rxjavatest.collection;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by zhangmin on 2018/11/30.
 */

public class TreeDemo {

    public static void main(String[] args) {

        int a = 0;
        do {

            a++;

            System.out.println("key : " + a +"  start");
            if(a == 3){
                return;
            }

            System.out.println("key : " + a +"  end");

        } while (a != 5);


        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "张敏1");
        treeMap.put(1, "张敏2");
        treeMap.put(2, "秦伟");
        treeMap.put(3, "罗");
        treeMap.put(4, "戴磊");
        System.out.println("key : " + 1 + " value : " + treeMap.get(1));
        Iterator<Map.Entry<Integer, String>> iterator = treeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println("key : " + entry.getKey() + " value : " + entry.getValue());
        }
    }
}
