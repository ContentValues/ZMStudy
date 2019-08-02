package xdroid.mwee.com.rxjavatest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class MethodDemo {

    public static void main(String[] args) {
        Method[] methods = SampleClass.class.getMethods();
        Type[] parameters = methods[1].getGenericParameterTypes();
        for (int i = 0; i < parameters.length; i++) {
            System.out.println(parameters[i]);
        }


        getConstructor(SampleClass.class);


        String str="总会在某一个回眸的时刻醉了流年，濡湿了柔软的心.总会有某一个回眸的时刻醉了流年，濡湿了柔软的心";
        str=str.replaceAll("总会在.+?流年", "总会有某一个回眸的时刻醉了流年");
        System.out.println(str);

    }




    public static <T> Constructor<?> getConstructor(Class<T> script) {

        Constructor<?>[] constructors = script.getDeclaredConstructors();
        Constructor<?> constructor = null;

        for (Constructor<?> constructor1 : constructors) {
            if (constructor1.getGenericParameterTypes().length == 0) {
                constructor = constructor1;
                break;
            }
        }
        if (constructor == null) {
            throw new IllegalArgumentException("Script class must have constructor.");
        }

        return constructor;
    }
}

class SampleClass {
    private String sampleField;
    private int sampleField2;

    public SampleClass(){

    }

    public SampleClass(String sampleField,int sampleField2){
        this.sampleField = sampleField;
        this.sampleField2 = sampleField2;
    }



    public String getSampleField() {
        return sampleField;
    }

    public void setSampleField(String sampleField,int sampleField2) {
        this.sampleField = sampleField;
        this.sampleField2 = sampleField2;
    }
}


