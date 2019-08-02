package xdroid.mwee.com.rxjavatest.design;

/**
 * Created by zhangmin on 2018/12/4.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式
 * 又称生成器模式：将一个复杂的构建与其表示相分离，使得同样的构建过程可以创建不同的表示
 *
 *  1 建造者角色：定义生成实例所需要的所有方法；
 *  2 具体的建造者角色：实现生成实例所需要的所有方法，并且定义获取最终生成实例的方法
 *  3 定义使用建造者角色中的方法来生成实例的方法
 *
 *
 * 我们假设一个快餐店的商业案例，其中，一个典型的套餐可以是一个汉堡（Burger）和一杯冷饮（Cold drink）。汉堡（Burger）可以是素食汉堡（Veg Burger）或鸡肉汉堡（Chicken Burger），它们是包在纸盒中。冷饮（Cold drink）可以是可口可乐（coke）或百事可乐（pepsi），它们是装在瓶子中。
 * <p>
 * 我们将创建一个表示食物条目（比如汉堡和冷饮）的 Item 接口和实现 Item 接口的实体类，以及一个表示食物包装的 Packing 接口和实现 Packing 接口的实体类，汉堡是包在纸盒中，冷饮是装在瓶子中。
 * <p>
 * 然后我们创建一个 Meal 类，带有 Item 的 ArrayList 和一个通过结合 Item 来创建不同类型的 Meal 对象的 MealBuilder。BuilderPatternDemo，我们的演示类使用 MealBuilder 来创建一个 Meal。
 */
public class D1_4 {


    public static void main(String[] args) {

        MealBuilder builder = new MealBuilder();
        Meal vegMeal = builder.prepareVegMeal();

        System.out.println(vegMeal.getCost());
        vegMeal.showItems();


        System.out.println("------------");

        Meal noVegMeal = builder.prepareNonVegMeal();

        System.out.println(noVegMeal.getCost());
        noVegMeal.showItems();


    }


    //汉堡包 名字 价格 打包盒
    interface Item {

         String aa = "";

          String name();

        float price();

        Packing packing();
    }

    //打包盒
    interface Packing {
        String pack();
    }

    //纸盒
    static class Wrapper implements Packing {

        @Override
        public String pack() {
            return "Wrapper";
        }
    }

    //瓶子
    static class Bottle implements Packing {

        @Override
        public String pack() {
            return "Bottle";
        }
    }

    //汉堡包抽象类
    static abstract class Burger implements Item {

        @Override
        public Packing packing() {
            return new Wrapper();
        }
    }


    static class VegBurger extends Burger {

        @Override
        public String name() {
            return "素食汉堡";
        }

        @Override
        public float price() {
            return 25;
        }
    }


    static class ChickenBurger extends Burger {

        @Override
        public String name() {
            return "鸡肉汉堡";
        }

        @Override
        public float price() {
            return 75;
        }
    }


    //冷饮抽象类
    static abstract class ColdDrink implements Item {

        @Override
        public Packing packing() {
            return new Bottle();
        }
    }

    static class Pepsi extends ColdDrink {

        @Override
        public String name() {
            return "百事可乐";
        }

        @Override
        public float price() {
            return 20;
        }
    }


    static class Coke extends ColdDrink {

        @Override
        public String name() {
            return "可口可乐";
        }

        @Override
        public float price() {
            return 10;
        }
    }


    /**
     * 食物集合
     */
    static class Meal {

        List<Item> items = new ArrayList<>();

        public void addItem(Item item) {
            items.add(item);
        }

        public float getCost() {
            float cost = 0.0f;
            for (Item item : items) {
                cost += item.price();
            }
            return cost;
        }

        public void showItems() {
            for (Item item : items) {
                System.out.print("Item : " + item.name());
                System.out.print(", Packing : " + item.packing().pack());
                System.out.println(", Price : " + item.price());
            }
        }
    }

    static class MealBuilder {

        //素食套餐
        public Meal prepareVegMeal() {
            Meal meal = new Meal();
            meal.addItem(new VegBurger());
            meal.addItem(new Coke());
            return meal;
        }

        //鸡肉套餐
        public Meal prepareNonVegMeal() {
            Meal meal = new Meal();
            meal.addItem(new ChickenBurger());
            meal.addItem(new Pepsi());
            return meal;
        }
    }

}
