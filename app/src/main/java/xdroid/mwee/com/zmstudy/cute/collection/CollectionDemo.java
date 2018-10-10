package xdroid.mwee.com.zmstudy.cute.collection;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhangmin on 2018/8/26.
 */

public class CollectionDemo {

    public final int a = 5;
    public final int b = 10;
    public static int c = 20;


    public final Student student = null;




    public void testCollection() {

        Collection<Integer> collection = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            collection.add(i);
            //collection.add(i);
        }

        System.out.println("测试结果--->" + collection.toArray());
        System.out.println("测试结果--->" + collection.toArray().toString());
        System.out.println("测试结果--->" + Arrays.toString(collection.toArray()));

        Integer[] integers = {11, 12, 14};
        collection.addAll(Arrays.asList(integers));

        collection.addAll(Arrays.asList(15, 19));

        Iterator<Integer> iterator = collection.iterator();
        while (iterator.hasNext()) {

            System.out.println("测试结果--->" + iterator.next());
        }
    }


    public void testCollection2() {


        List<Student> collection = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Student student = new Student("张敏", i);
            collection.add(student);
        }


        /*Student student = new Student("张敏", 1);
        collection.add(student);*/

       /* System.out.println("测试结果--->" + collection.toArray());
        System.out.println("测试结果--->" + collection.toArray().toString());
        System.out.println("测试结果--->" + Arrays.toString(collection.toArray()));*/


       /* Student student = new Student("张敏大屌", 1);

        if (collection.contains(student)) {
            System.out.println("哈哈哈--->" + student.toString());
        }*/

        //Arrays.sort(collection, );

      /*  Iterator<Student> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Student studentB = iterator.next();
            System.out.println("测试结果--->" + studentB);
        }*/

        /*Student student11 = new Student("张敏", 1);
        LinkedList<Student> linkedList = new LinkedList<>();
        linkedList.addAll(collection);*/


        //System.out.println("哈哈哈--->" + linkedList.contains(student11));
        //System.out.println("哈哈哈--->" + linkedList.contains(student));


        Collections.sort(collection, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.year > o2.year) {
                    return -1;
                }
                if (o1.year < o2.year) {
                    return 1;
                }
                return 0;
            }
        });
        Iterator<Student> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Student studentB = iterator.next();
            System.out.println("测试结果--->" + studentB);
        }


    }


    class StudentComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return 0;
        }
    }


    public void testLinkList() {

        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("张敏");
        linkedList.add("张敏1");
        linkedList.add("张敏2");
        linkedList.add("张敏2");
        linkedList.add("张敏3");

       /* System.out.println("测试结果--->element()" + linkedList.element());

        System.out.println("测试结果--->peek()" + linkedList.peek());*/

        //System.out.println("测试结果--->poll()" + linkedList.poll());

        //System.out.println("测试结果--->peek()" + linkedList.peek());

       /* System.out.println("测试结果--->poll()" + linkedList.poll());
        System.out.println("测试结果--->poll()" + linkedList.poll());
        System.out.println("测试结果--->poll()" + linkedList.poll());
        System.out.println("测试结果--->poll()" + linkedList.poll());*/

        ;
        System.out.println("测试结果--->linkedList.indexOf(张敏)" + linkedList.indexOf("张敏"));
        System.out.println("测试结果--->linkedList.indexOf(张敏1)" + linkedList.indexOf("张敏1"));
        System.out.println("测试结果--->linkedList.indexOf(张敏3)" + linkedList.indexOf("张敏3"));


    }


    class Student {

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", year=" + year +
                    '}';
        }

        String name;
        Integer year;

        public Student(String name, int year) {
            this.name = name;
            this.year = year;
        }

        @Override
        public boolean equals(Object obj) {
            Student student = (Student) obj;

            return student.year == year;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

    }

}
