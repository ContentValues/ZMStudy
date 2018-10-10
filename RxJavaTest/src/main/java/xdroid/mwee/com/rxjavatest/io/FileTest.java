package xdroid.mwee.com.rxjavatest.io;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.CharBuffer;

/**
 * Created by zhangmin on 2018/9/19.
 */

public class FileTest {

    public static void main(String[] args) {

        //获取当前项目绝对路径(不是xxx.java文件路径哦)
       /* File file = new File(".");
        System.out.println(file.getAbsolutePath());

        //用相对路径获取上级文件
        File file2 = new File("/Users/zhangmin/Documents/城市规划");
        System.out.println(file2.exists());


        try {
            File file4 = new File("/Users/zhangmin/Documents/城市规划/test.txt");
            if (!file4.exists()) {
                System.out.println("创建成功了吗file4?" + file4.createNewFile());
            }
            File file5 = new File("/Users/zhangmin/Documents/城市规划", "test2.txt");
            if (!file5.exists()) {
                System.out.println("创建成功了吗file5?" + file5.createNewFile());
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file4);
            fileOutputStream.write("张敏好爽啊".getBytes());
            fileOutputStream.write("秦伟好爽啊".getBytes());
            fileOutputStream.write("张普好爽啊".getBytes());
            //fileOutputStream.write("读取数据好爽啊".getBytes());
            fileOutputStream.flush();

            InputStream inputStream = new FileInputStream(file4);
            byte b[] = new byte[(int) file4.length()];
            inputStream.read(b);
            inputStream.close();

            System.out.println("inputStream 读取数据-  inputStream->" + new String(b));


            *//*InputStream inputStream2 = new FileInputStream(file4);
            byte byte2[] = new byte[1024];
            int len = 0;
            int temp = 0;
            while ((temp = inputStream2.read()) != -1) {
                byte2[len] = (byte) temp;
                len++;
            }
            inputStream2.close();
            System.out.println("inputStream 读取数据-  inputStream2 ->" + new String(byte2, 0, len));*//*


            InputStream inputStream2 = new FileInputStream(file4);
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[16];
            int len = -1;
            while ((len = inputStream2.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
                //outSteam.write(buffer);
            }
            outSteam.close();
            inputStream2.close();
            System.out.println("inputStream 读取数据-  inputStream->" + new String(outSteam.toByteArray()));


            //FileInputStream fileInputStream = new FileInputStream(file4);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        File file = new File("/Users/zhangmin/Documents/城市规划", "test2.txt");
        //write(file, "2222333121212133131212133xdroidmweecomrxjavatestxdroi张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈");
        writeChar(file, "222xdr张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈张敏好帅哈哈哈哈哈");
        //write(file, "2222333121212133131212133xdroidmweecomrxjavatestxdroidmweecomrxjavatestxdroidmweecomrxjavatestxdroidmweecomrxjavatestxdroidmweecomrxjavatestxdroidmweecomrxjavatest");

        System.out.println("read0--->" + read0(file));

        System.out.println("read1--->" + read1(file));
        System.out.println("read2--->" + read2(file));
        System.out.println("read3--->" + read3(file));
        System.out.println("read4--->" + read4(file));

        System.out.println("readChar--->" + readChar(file));

    }


    /**
     * 输入字节流
     * <p>
     * <p>
     * todo ASCII码： 一个英文字母（不分大小写）占一个字节的空间，一个中文汉字占两个字节的空间
     * todo UTF-8编码：一个英文字符等于一个字节，一个中文（含繁体）等于二~四个字节  目前此项目中相当于三个字节 所以 new byte[3*N]去接受读取数据是没问题的
     * <p>
     * todo 此种写法只能读取英文和数字没问题 但是读取文字的时候 字节换算有问题
     *
     * @param file
     * @return
     */
    private static String read0(File file) {
        try {
            InputStream inputStream = new FileInputStream(file);
            byte byteArray[] = new byte[12];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(byteArray)) != -1) {
                sb.append(new String(byteArray, 0, len));
            }
            inputStream.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


   /* private static String readZERO(File file) {
        try {
            InputStream inputStream = new FileInputStream(file);
            byte byteArray[] = new byte[128];
            inputStream.read(byteArray);
            return new String(byteArray, 0, byteArray.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }*/

    /*private static String readZERO1(File file) {
        try {
            InputStream inputStream = new FileInputStream(file);
            byte byteArray[] = new byte[128];
            int len = inputStream.read(byteArray);
            return new String(byteArray, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }*/


    private static String read1(File file) {
        try {
            InputStream inputStream = new FileInputStream(file);
            byte byteArray[] = new byte[(int) file.length()];
            inputStream.read(byteArray);
            inputStream.close();
            return new String(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }

    private static String read2(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[16];
            int len = -1;
            while ((len = inputStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.flush();
            outSteam.close();
            inputStream.close();
            return new String(outSteam.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    private static String read3(File file) {
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = dataInputStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.flush();
            outSteam.close();
            dataInputStream.close();
            return new String(outSteam.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String read4(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            byte[] buffer = new byte[dataInputStream.available()];
            dataInputStream.read(buffer);
            dataInputStream.close();
            return new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 字节输出流
     *
     * @param file
     * @param mContent
     */
    private static void write(File file, String mContent) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(mContent.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*--------------IO字符流-----------------*/
    private static String readChar(File file) {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            char[] buf = new char[16];
            StringBuilder stringBuilder = new StringBuilder();
            int len;
            while ((len = fileReader.read(buf)) != -1) {
                stringBuilder.append(new String(buf, 0, len));
            }
            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * @param file
     * @param mContent
     */
    private static void writeChar(File file, String mContent) {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(mContent);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param file
     * @param mContent
     */
    private static void writeChar2(File file, String mContent) {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(mContent);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
