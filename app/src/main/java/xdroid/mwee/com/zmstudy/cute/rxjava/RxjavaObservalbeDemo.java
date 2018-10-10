package xdroid.mwee.com.zmstudy.cute.rxjava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.functions.Func1;
import rx.internal.operators.OperatorAsObservable;
import rx.internal.operators.OperatorDistinct;
import rx.internal.util.ActionSubscriber;
import rx.internal.util.InternalObservableUtils;
import rx.schedulers.Schedulers;

/**
 * Created by zhangmin on 2018/8/9.
 */

public class RxjavaObservalbeDemo {

    public void observableTest1() {
        //观察者
        Subscriber<String> observer = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError()");
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext---->" + s);
            }

            @Override
            public void onStart() {
                super.onStart();
                System.out.println("onStart()");
            }
        };
        //被观察者
        Observable observable = Observable.unsafeCreate(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext("Subscriber");
                subscriber.onCompleted();
                subscriber.onNext("hello");
                subscriber.onNext("world");
                subscriber.onNext("!!!!");

            }
        }).doOnNext(new Action1<String>() {
            @Override
            public void call(String s) {

                System.out.println("doOnNext");
            }
        });
        observable.subscribe(observer);


        //Observable.just()
     /*   Observable.create(new SyncOnSubscribe<String, String>() {
            @Override
            protected String generateState() {
                return null;
            }

            @Override
            protected String next(String state, Observer<? super String> observer) {
                observer.onNext("Subscriber");
                observer.onNext("hello");
                observer.onNext("world");
                observer.onNext("!!!!");
                observer.onCompleted();
                return "张敏好帅";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("Action1()--->" + s);
            }
        });*/
    }

    public void observableTest2() {


       /* String lastMealNumber = "A162";
        System.out.println("Action1--->" + Integer.parseInt(lastMealNumber.substring(1, lastMealNumber.length())));

        String[] str = {"4", "5", "6"};
        Observable.from(str)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("Action1--->" + s);
                    }
                });*/

      /*  Observable.just("1", "2", "3").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("Action1--->" + s);
            }
        });*/

        Observable.just("张敏好爽").lift(new Observable.Operator<Student, String>() {
            @Override
            public Subscriber<? super String> call(Subscriber<? super Student> subscriber) {
                return new ActionSubscriber<String>(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Student student = new Student();
                        student.nameStudent = s;
                        for (int i = 0; i < 3; i++) {
                            Course course = new Course();
                            course.nameCourse = "张敏course--->" + i;
                            student.courseList.add(course);
                        }
                        subscriber.onNext(student);
                    }
                }, InternalObservableUtils.ERROR_NOT_IMPLEMENTED, Actions.empty());
            }
        }).subscribe(new Action1<Student>() {
            @Override
            public void call(Student student) {
                System.out.println("Action1--->" + student.nameStudent);
            }
        });



        Observable.just("张敏好爽2").lift(new Observable.Operator<Student, String>() {
            @Override
            public Subscriber<? super String> call(Subscriber<? super Student> subscriber) {
                return null;
            }
        }).subscribe(new Action1<Student>() {
            @Override
            public void call(Student student) {
                System.out.println("Action1--->" + student.nameStudent);
            }
        });

        /*Observable.just("张敏好爽3").map(new Func1<String, Student>() {

            @Override
            public Student call(String s) {
                Student student = new Student();
                student.nameStudent = s;
                for (int i = 0; i < 3; i++) {
                    Course course = new Course();
                    course.nameCourse = "张敏course--->" + i;
                    student.courseList.add(course);
                }
                return student;
            }
        }).subscribe(new Action1<Student>() {
            @Override
            public void call(Student student) {
                System.out.println("Action2--->" + student.nameStudent);
            }
        });*/

    }


    public void observableTimer() {


        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("---timer onCompleted---");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("---timer onError---");
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("---timer onNext---" + aLong);
            }
        });


        Observable.interval(10, 2, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("---10秒倒计时已结束 interval onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("---10秒倒计时已结束 interval onError---");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("---10秒倒计时已结束 interval onNext---" + aLong);
                    }
                });

    }


    public void observableEmpty() {

        Observable.empty().subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                System.out.println("---timer onCompleted---");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("---timer onError---");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("---timer onNext---" + o);
            }
        });

    }


    public void flatTest() {

        List<Student> students = new ArrayList<>();

        Student student = new Student();
        student.nameStudent = "张敏";
        for (int i = 0; i < 3; i++) {
            Course course = new Course();
            course.nameCourse = "张敏course--->" + i;
            student.courseList.add(course);
        }
        for (int i = 0; i < 3; i++) {
            Course course = new Course();
            course.nameCourse = "张敏course--->" + i;
            student.courseList.add(course);
        }
        students.add(student);

        Student student1 = new Student();
        student1.nameStudent = "大逼哥";
        for (int i = 0; i < 3; i++) {
            Course course = new Course();
            course.nameCourse = "大逼哥course--->" + i;
            student1.courseList.add(course);
        }
        students.add(student1);

        Student student2 = new Student();
        student2.nameStudent = "谈及吧";
        for (int i = 0; i < 3; i++) {
            Course course = new Course();
            course.nameCourse = "谈及吧course--->" + i;
            student2.courseList.add(course);
        }
        students.add(student2);


        Observable.from(students).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.courseList);
            }
        }).flatMap(new Func1<Course, Observable<String>>() {
            @Override
            public Observable<String> call(Course course) {
                return Observable.just(course.nameCourse);
            }
        })/*.filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return s.contains("0");
            }
        }).distinct()
                .takeLast(3)*/
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("Action1()-->" + s);
                    }
                });


    }


    class Student {


        public String nameStudent;

        public List<Course> courseList = new ArrayList<>();


    }

    class Course {

        public String nameCourse;


    }


}
