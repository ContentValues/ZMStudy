package xdroid.mwee.com.zmstudy.ui;

import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.cute.rxjava.RxjavaObservalbeDemo;

/**
 * Created by zhangmin on 2018/4/8.
 */

public class RxJavaTestActitviy extends AppCompatActivity {


    private final static String TAG = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        findViewById(R.id.tvTest1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rxjavaText1();
                //rxjavaTest3();
                //rxjava0();
                System.out.println("tvTest1 onClickListener !!!!!!!");
                RxjavaObservalbeDemo demo = new RxjavaObservalbeDemo();
                demo.observableTimer();
            }
        });

        findViewById(R.id.tvTest2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rxjavaText2();
                //test1();
                System.out.println("tvTest2 onClickListener !!!!!!!");
            }
        });
    }

    private void rxjava0() {

        Observable observable1 = Observable.just(1, 2, 3);
        Observable observable2 = Observable.just(3, 4);
        Observable.zip(observable1, observable2, new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                Log.d(TAG, "call---->zip-->" + integer + "---->+" + integer2);
                return integer + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "Action1---->zip-->" + integer);
            }
        });






        /*Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                for (int i = 0; i < 10; i++) {

                    subscriber.onNext(i + "");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }


            }
        }).debounce(1, TimeUnit.SECONDS).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext---->debounce-->" + s);
            }
        });*/


        /*Observable.just(1, 2, 3, 2, 3).distinct().filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 2;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext---->distinct-->" + integer);
            }
        });*/
/*
        Observable.just(1, 2, 3, 2, 3).ignoreElements().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted---->distinct-->");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError---->distinct-->");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext---->distinct-->" + integer);
            }
        });*/


    }


    private void test1() {

        /**
         *  输出每一个学生的课程名称
         */

        List<Student> students = new ArrayList<>();

        Student student = new Student();
        student.nameStudent = "张敏";
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



       /* Observer<Course> observer = new Observer<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.d(TAG, course.nameCourse);
            }
        };*/

        Observable.from(students).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.courseList);
            }
        }).subscribe(new Action1<Course>() {
            @Override
            public void call(Course course) {
                Log.d(TAG, "Action1---->" + course.nameCourse);
            }
        });


        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.courseList);
                    }
                }).flatMap(new Func1<Course, Observable<String>>() {
            @Override
            public Observable<String> call(Course course) {
                return Observable.just(course.nameCourse);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "Action2---->" + s);
            }
        });


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
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String o) {
                Log.d(TAG, "observer---->" + o);
            }
        });


    }


    private void rxjavaText1() {


      /*  //被观察者
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext("Observable");
                subscriber.onNext("hello");
                subscriber.onNext("world");
                subscriber.onNext("!!!!");
                subscriber.onCompleted();

            }
        });

        //观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted--->");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError---->" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext---->" + s);
            }
        };

        observable.subscribe(observer);*/

        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                //subscriber.onCompleted();
                Log.d(TAG, "new Observable<Object>()");
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    Log.d(TAG, "new Observable---->主线程");
                } else {
                    Log.d(TAG, "new Observable---->子线程");
                }
                subscriber.onNext(new Object());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        Log.d(TAG, "new Action1<Object>()");
                        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                            Log.d(TAG, "new Action1---->主线程");
                        } else {
                            Log.d(TAG, "new Action1---->子线程");
                        }
                    }
                });

    }


    private void rxjavaText2() {

        //观察者
        Subscriber<String> observer = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted--->");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError---->" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext---->" + s);
            }

            @Override
            public void onStart() {
                super.onStart();

                Log.d(TAG, "哈哈哈哈  你好  onStart   ");
            }
        };


        //被观察者
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext("Subscriber");
                subscriber.onNext("hello");
                subscriber.onNext("world");
                subscriber.onNext("!!!!");
                subscriber.onCompleted();

            }
        });

        observable.subscribe(observer);
    }


    /**
     * 异步线程 子线程耗时操作 主线程刷新UI
     */
    private void rxjavaTest3() {


        Observable.just("1", "2", "3").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });


        Observable.just("1", "2", "3")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });

        String[] str = {"4", "5", "6"};
        Observable.from(str)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });


        Observable.from(str)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });


        Observable.from(str)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                });

        Observable.from(str)
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                });


    }


    /**
     * 变换
     */
    private void rxjavaTest4() {


        List<Student> students = new ArrayList<>();

        Student student = new Student();
        student.nameStudent = "张敏";
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


        //输出每个学生所对应的课程的名称

        final Observer<Course> observer = new Observer<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.d(TAG, "observer--->" + course.nameCourse);
            }
        };


        Action1<Course> action1 = new Action1<Course>() {
            @Override
            public void call(Course course) {
                Log.d(TAG, "Action1---->" + course.nameCourse);
            }
        };


        Action1<String> action2 = new Action1<String>() {
            @Override
            public void call(String nameCourse) {
                Log.d(TAG, "Action2" + nameCourse);
            }
        };


        Observable.just(student, student1, student2)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.courseList);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);


        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.courseList);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.courseList);
                    }
                })
                .flatMap(new Func1<Course, Observable<String>>() {
                    @Override
                    public Observable<String> call(Course course) {
                        return Observable.just(course.nameCourse);
                    }
                }).subscribe(action2);


    }


    class Student {

        public String nameStudent;

        public List<Course> courseList = new ArrayList<>();


    }

    class Course {

        public String nameCourse;


    }
}
