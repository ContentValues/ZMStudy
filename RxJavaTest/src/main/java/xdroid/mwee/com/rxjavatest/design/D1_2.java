package xdroid.mwee.com.rxjavatest.design;

/**
 * Created by zhangmin on 2018/12/4.
 */

/**
 * 工厂模式
 */
public class D1_2 {


    public static void main(String[] args) {

        ShapeFactory shapeFactory = new ShapeFactory();

        Shape rectangle = shapeFactory.getShape("RECTANGLE");
        rectangle.draw();

        Shape square = shapeFactory.getShape("SQUARE");
        square.draw();

        Shape circle = shapeFactory.getShape("CIRCLE");
        circle.draw();

    }


    interface Shape {

        void draw();
    }

    static class Rectangle implements Shape {

        @Override
        public void draw() {
            System.out.println("Inside Rectangle::draw() method.");
        }
    }

    static class Square implements Shape {

        @Override
        public void draw() {
            System.out.println("Inside Square::draw() method.");
        }
    }

    static class Circle implements Shape {

        @Override
        public void draw() {
            System.out.println("Inside Circle::draw() method.");
        }
    }

    static class ShapeFactory {

        public Shape getShape(String shapeType) {

            if (shapeType == null) {
                return null;
            }
            if (shapeType.equalsIgnoreCase("CIRCLE")) {
                return new Circle();
            } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
                return new Rectangle();
            } else if (shapeType.equalsIgnoreCase("SQUARE")) {
                return new Square();
            }

            return null;
        }

    }

}
