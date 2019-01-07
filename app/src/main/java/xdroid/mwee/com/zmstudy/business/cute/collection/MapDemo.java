package xdroid.mwee.com.zmstudy.business.cute.collection;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by zhangmin on 2018/8/31.
 */

public class MapDemo {


    public static void testSystem() {


        // 初始化
        int[] ids = { 1, 2, 3, 4, 5 };

        System.arraycopy(ids, 0, ids, 3, 2);
        System.out.println(Arrays.toString(ids)); // [1, 2, 3, 1, 2]

        int[] ids2 = new int[6];
        System.arraycopy(ids, 1, ids2, 0, 3);
        System.out.println(Arrays.toString(ids2));

        /*Object[] objects1 = Arrays.asList("1", "2", "3", "4").toArray();
        Object[] objects1Copy = new ArrayList<>().toArray();

        System.arraycopy(objects1, 0, objects1Copy, 0, 2);

        System.out.println("System.list-->" + Arrays.toString(objects1));
        System.out.println("System.arraycopy-->" + Arrays.toString(objects1Copy))*/;


    }


    public static void testLinkList() {

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

       /* linkedList.add(2);
        linkedList.add(3);*/

        System.out.println("abstractCollection 集合 --->" + Arrays.toString(linkedList.toArray()));

       /* Collections.sort(linkedList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                }
                if (o1 < o2) {
                    return 1;
                }
                return 0;
            }
        });
        System.out.println("abstractCollection 集合 --->" + Arrays.toString(linkedList.toArray()));
*/

        System.out.println("Collections max --->" + Collections.max(linkedList));
        System.out.println("Collections min --->" + Collections.min(linkedList));


        System.out.println("linkedList.peek() --->" + linkedList.peek());
        System.out.println("linkedList 集合 --->" + Arrays.toString(linkedList.toArray()));

        System.out.println("linkedList.peek() --->" + linkedList.element());
        System.out.println("linkedList 集合 --->" + Arrays.toString(linkedList.toArray()));


        System.out.println("linkedList.getFirst() --->" + linkedList.getFirst());
        System.out.println("linkedList 集合 --->" + Arrays.toString(linkedList.toArray()));


        System.out.println("linkedList.peekLast() --->" + linkedList.peekLast());
        System.out.println("linkedList 集合 --->" + Arrays.toString(linkedList.toArray()));


        System.out.println("linkedList.getLast() --->" + linkedList.getLast());
        System.out.println("linkedList 集合 --->" + Arrays.toString(linkedList.toArray()));


        System.out.println("linkedList.poll() --->" + linkedList.poll());
        System.out.println("linkedList 集合 --->" + Arrays.toString(linkedList.toArray()));

        System.out.println("linkedList.pollFirst() --->" + linkedList.pollFirst());
        System.out.println("linkedList 集合 --->" + Arrays.toString(linkedList.toArray()));

        System.out.println("linkedList.pollLast() --->" + linkedList.pollLast());
        System.out.println("linkedList 集合 --->" + Arrays.toString(linkedList.toArray()));


        System.out.println("linkedList.offerFirst() --->" + linkedList.offerFirst(88));
        System.out.println("linkedList 集合 --->" + Arrays.toString(linkedList.toArray()));

        System.out.println("linkedList.offerFirst() --->" + linkedList.offerLast(88));
        System.out.println("linkedList 集合 --->" + Arrays.toString(linkedList.toArray()));


       /* AbstractCollection<Integer> abstract1 = new ArrayList<>();
        abstract1.add(2);
        abstract1.add(3);*/

        //abstractCollection.retainAll(abstract1);
       /* linkedList.removeAll(abstract1);
        System.out.println("abstractCollection.retainAll(abstract1) 集合 --->"+Arrays.toString(linkedList.toArray()));
        List<Integer> integerList = linkedList.subList(0,2);
        System.out.println("abstractCollection.subList(0,2) 集合 --->"+Arrays.toString(integerList.toArray()));*/


    }




    public static void testCollection() {


        MyClass myClass1 = new MyClass();
        MyClass myClass2 = new MyClass();
        System.out.println(myClass1.i);
        System.out.println(myClass1.j);
        System.out.println(myClass2.i);
        System.out.println(myClass2.j);


        String a = "hello2";

        final String b = "hello";

        String d = "hello";

        String c = b + 2;

        String e = d + 2;


        System.out.println("Collections a --->" + a);
        System.out.println("Collections b --->" + b);
        System.out.println("Collections c --->" + c);
        System.out.println("Collections d --->" + d);
        System.out.println("Collections e --->" + e);

        System.out.println(a == c);
        System.out.println(a == e);




        ArrayList<Integer> abstractCollection = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            abstractCollection.add(i);
        }

        Collections.sort(abstractCollection, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                }
                if (o1 < o2) {
                    return 1;
                }
                return 0;
            }
        });

        System.out.println("abstractCollection 集合 --->" + abstractCollection.toArray());
        System.out.println("abstractCollection 集合 --->" + Arrays.toString(abstractCollection.toArray()));

        System.out.println("Collections max --->" + Collections.max(abstractCollection));
        System.out.println("Collections min --->" + Collections.min(abstractCollection));


        AbstractCollection<Integer> abstract1 = new ArrayList<>();
        abstract1.add(2);
        abstract1.add(3);

        //abstractCollection.retainAll(abstract1);
        abstractCollection.removeAll(abstract1);
        System.out.println("abstractCollection.retainAll(abstract1) 集合 --->" + Arrays.toString(abstractCollection.toArray()));
        List<Integer> integerList = abstractCollection.subList(0, 2);
        System.out.println("abstractCollection.subList(0,2) 集合 --->" + Arrays.toString(integerList.toArray()));

    }


    public static void testIterator() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            iterator.remove();
        }
        System.out.println("HashMap values 键-->" + Arrays.toString(list.toArray()));
    }


    public static void testIterator2() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer == 2)
                iterator.remove();
        }
        System.out.println("HashMap values 键-->" + Arrays.toString(list.toArray()));
    }


    public static void testHashMap() {

        Map<String, String> hashMap = Collections.synchronizedMap(new HashMap<>());
        //Map<String, String> hashMap = new HashMap<String, String>();
        //HashMap<String, String> hashMap2 = Collections.synchronizedMap(hashMap);
        hashMap.put("1", "张敏");
        hashMap.put("1", "张敏");
        hashMap.put("1", "张敏3");
        hashMap.put("4", "张敏");
        hashMap.put("3", "张敏");
        hashMap.put("2", "张敏");
        hashMap.put("null", "null");
        //hashMap.put("null", "哈哈空");
        Set<Map.Entry<String, String>> entrySet = hashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("HashMap  键-->" + entry.getKey() + "       值-->" + entry.getValue());
        }

        for (String s : hashMap.keySet()) {
            System.out.println("HashMap values 键-->" + s);
        }

        for (String s : hashMap.values()) {
            System.out.println("HashMap values 值-->" + s);
        }
    }


    public static void testTreeMap() {

        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("1", "张敏");
        treeMap.put("1", "张敏");
        treeMap.put("1", "张敏3");
        treeMap.put("4", "张敏");
        treeMap.put("3", "张敏");
        treeMap.put("2", "张敏");
        treeMap.put("null", "null");
        treeMap.put("null", "哈哈空");
        Set<Map.Entry<String, String>> entrySet = treeMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("TreeMap  键-->" + entry.getKey() + "       值-->" + entry.getValue());
        }
    }
}
