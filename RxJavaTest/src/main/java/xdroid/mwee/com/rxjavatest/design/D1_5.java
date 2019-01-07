package xdroid.mwee.com.rxjavatest.design;

/**
 * Created by zhangmin on 2018/12/4.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式 一对多
 */
public class D1_5 {


    public static void main(String[] args) {

//        Student student = new Student();
//        student.age = 18;
//
//        Student temp = null;
//
//        temp = student;
//
//        temp.age = 12;
//
//
//        System.out.println("a--->"+student.age);
//        System.out.println("b--->"+temp.age);

        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        subject.setState(10);

        subject.setState(15);


    }


    static class Student{

        public int age = 10;
    }



    /**
     *  被观察者者持有所有的观察者
     *
     *  被观察者有一个状态变更的标志 然后通知个个观察者
     *
     *  被观察者提供 添加观察者以及通知的入口
     *
     *
     *
     */
    static class Subject {

        List<Observer> observers = new ArrayList<>();

        private int state;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
            notifyAllObservers();
        }

        public void attach(Observer observer) {
            observers.add(observer);
        }

        public void notifyAllObservers() {
            for (Observer observer : observers) {
                observer.update();
            }
        }


    }

    abstract static class Observer {

        Subject subject;

        abstract void update();
    }

   static class BinaryObserver extends Observer {

        public BinaryObserver(Subject subject) {
            this.subject = subject;
            this.subject.attach(this);
        }

        @Override
        void update() {
            System.out.println("Binary String: "
                    + Integer.toBinaryString(subject.getState()).toUpperCase());
        }
    }

   static class OctalObserver extends Observer {

        public OctalObserver(Subject subject) {
            this.subject = subject;
            this.subject.attach(this);
        }


        @Override
        void update() {
            System.out.println("Octal String: "
                    + Integer.toOctalString(subject.getState()).toUpperCase());
        }
    }

    static class HexaObserver extends Observer {

        public HexaObserver(Subject subject) {
            this.subject = subject;
            this.subject.attach(this);
        }


        @Override
        void update() {
            System.out.println("Hex String: "
                    + Integer.toHexString(subject.getState()).toUpperCase());
        }
    }


}
