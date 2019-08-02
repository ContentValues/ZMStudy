package xdroid.mwee.com.rxjavatest.design;

/**
 * Created by zhangmin on 2019/3/15.
 */


/***
 *
 * 我们采用extends，super来扩展泛型的目的是为了弥补例如List<E>只能存放一种特定类型数据的不足，
 * 将其扩展为List<? extends E> 使其可以接收E的子类型中的任何一种类型元素，这样使它的使用范围更广
 * List<? super E>同理。
 *
 */
public class D1_7 {


    public static void main(String[] args) {

        Plate<Food> plate = new Plate<>();

        Food food = new Food();
        Fruit fruit = new Fruit();
        Apple apple = new Apple();

        plate.set(food);
        plate.set(fruit);
        plate.set(apple);

        Food fruitGet = plate.get();

    }


    /**
     * 上界通配符  Plate<？ extends Fruit>
     * <p>
     * 所有元素都是Fruit的子类(包含本身)
     *
     * List<? extends E>表示该list集合中存放的都是E的子类型（包括E自身），由于E的子类型可能有很多，
     * 但是我们存放元素时实际上只能存放其中的一种子类型
     * （这是为了泛型安全，因为其会在编译期间生成桥接方法<Bridge Methods>该方法中会出现强制转换，若出现多种子类型，则会强制转换失败），例子如下：
     * List<? extends Number> list=new ArrayList<Number>();
     * list.add(4.0);//编译错误
     * list.add(3);//编译错误
     *
     * todo 上例中添加的元素类型不止一种，这样编译器强制转换会失败，为了安全，Java只能将其设计成不能添加元素。
     *
     * todo 虽然List<? extends E>不能添加元素，但是由于其中的元素都有一个共性--有共同的父类，因此我们在获取元素时可以将他们统一强制转换为E类型，我们称之为get原则
     *
     */
    private static void testUp() {

        Plate<? extends Fruit> plate = new Plate<>();

        Fruit fruit = new Fruit();

        Apple apple = new Apple();

//        plate.set(fruit);
//        plate.set(apple);

        Fruit fruitGet = plate.get();

    }


    /**
     * 所有元素都是Fruit的父类(包含本身)
     *
     *
     * List <? super Fruit> 假设：Fruit有子类A、B、C 那么 list.add(A);list.add(B);list.add(C); 这却是可以的，为什么呢：
     * 因为他是这么存的：list.add((Fruit)A);list.add((Fruit)B); 自动强转了。因为 小转大是隐性的，大转小才是强转需要加类型。[plate.set(fruit); 可以]
     *
     *
     * 那这里为什么又不能存Fruit的父类呢？ 因为 它是？号，类型代表待定，不跑起来他也不知道你到底存的什么。
     * 所以我们能手动add()进去的数据都必须是绝对安全的(最级父类:本身)才能通过。所以直接add父类也是不行的。[plate.set(food);报错]
     *
     *
     *
     * 对于List<? super E>其list中存放的都是E的父类型元素（包括E），我们在向其添加元素时，只能向其添加E的子类型元素（包括E类型）
     * ，这样在编译期间将其强制转换为E类型时是类型安全的，因此可以添加元素，例子如下： List<? super Number> list=new ArrayList<Number>();
     *
     * todo 但是，由于该集合中的元素都是E的父类型（包括E），其中的元素类型众多，在获取元素时我们无法判断是哪一种类型，故设计成不能获取元素，我们称之为put原则。
     */
    private static void testLower() {


        Plate<? super Fruit> plate = new Plate<>();
        Food food = new Food();
        Fruit fruit = new Fruit();
        Apple apple = new Apple();

        plate.set(fruit);
        plate.set(apple);

        /**
         *
         */
        //plate.set(food);

        //Apple fruit1 =  plate.get();


    }


    static class Food {

    }


    static class Fruit extends Food {

    }

    static class Apple extends Fruit {

    }

    static class Plate<T> {

        private T item;

//        public Plate(T t) {
//            this.item = t;
//        }

        public T get() {
            return item;
        }

        public void set(T t) {
            this.item = t;
        }

    }
}
