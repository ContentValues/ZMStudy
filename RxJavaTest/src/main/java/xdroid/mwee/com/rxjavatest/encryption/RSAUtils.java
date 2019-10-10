package xdroid.mwee.com.rxjavatest.encryption;

//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.security.Key;
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.util.Scanner;
//import javax.crypto.Cipher;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

public class RSAUtils {

    //默认公钥的持久化文件存放位置
//    private static String PUBLIC_KEY_FILE = "PublicKey";
//    //默认私钥的持久化文件存放位置
//    private static String PRIVATE_KEY_FILE = "PrivateKey";
//
//    //设置公私钥持久化文件的存放位置
//    public static void setKeyPath(String path) {
//        if (PUBLIC_KEY_FILE.equals("PublicKey")) {
//            PUBLIC_KEY_FILE = path + (path.endsWith("//") ? "PublicKey" : "/PublicKey");
//            PRIVATE_KEY_FILE = path + (path.endsWith("//") ? "PrivateKey" : "/PrivateKey");
//        }
//    }
//
//    //创建公私钥对
//    private static void createKeyPair() throws Exception {
//        //使用RSA算法获得密钥对生成器对象keyPairGenerator
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        //设置密钥长度为1024
//        keyPairGenerator.initialize(1024);
//        //生成密钥对
//        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//        //获取公钥
//        Key publicKey = keyPair.getPublic();
//        //获取私钥
//        Key privateKey = keyPair.getPrivate();
//        //保存公钥对象和私钥对象为持久化文件
//        ObjectOutputStream oos1 = null;
//        ObjectOutputStream oos2 = null;
//        try {
//            oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
//            oos2 = new ObjectOutputStream(
//                    new FileOutputStream(PRIVATE_KEY_FILE));
//            oos1.writeObject(publicKey);
//            oos2.writeObject(privateKey);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            oos1.close();
//            oos2.close();
//        }
//    }
//
//    //RSA加密
//    public static String encryptWithRSA(String str) throws Exception {
//        createKeyPair();
//        Key publicKey = null;
//        //读取持久化的公钥对象
//        ObjectInputStream ois = null;
//        try {
//            ois = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
//            publicKey = (Key) ois.readObject();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            ois.close();
//        }
//
//        //获取一个加密算法为RSA的加解密器对象cipher。
//        Cipher cipher = Cipher.getInstance("RSA");
//        //设置为加密模式,并将公钥给cipher。
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        //获得密文
//        byte[] secret = cipher.doFinal(str.getBytes());
//        //进行Base64编码
//        return new BASE64Encoder().encode(secret);
//    }
//
//    //RSA解密
//    public static String decryptWithRSA(String secret) throws Exception {
//        Key privateKey;
//        ObjectInputStream ois = null;
//        try {
//            ois = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
//            privateKey = (Key) ois.readObject();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            ois.close();
//        }
//        Cipher cipher = Cipher.getInstance("RSA");
//        //传递私钥，设置为解密模式。
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        //解密器解密由Base64解码后的密文,获得明文字节数组
//        byte[] b = cipher.doFinal(new BASE64Decoder().decodeBuffer(secret));
//        //转换成字符串
//        return new String(b);
//
//    }
//
//    public static void main(String[] args) throws Exception {
//        //设置公私钥对存放路径，可选，默认是工程目录
//        //RSAUtils.setKeyPath(str);
//        System.out.println("请输入明文：");
//        Scanner sca = new Scanner(System.in);
//        String str = sca.nextLine();
//        System.out.println("============================");
//        String secret = RSAUtils.encryptWithRSA(str);
//        System.out.println("经过RSA加密后的密文为：");
//        System.out.println(secret);
//        System.out.println("============================");
//        String original = RSAUtils.decryptWithRSA(secret);
//        System.out.println("经过RSA解密后的原文为：");
//        System.out.println(original);
//    }
}



