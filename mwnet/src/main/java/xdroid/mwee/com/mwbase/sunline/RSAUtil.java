package xdroid.mwee.com.mwbase.sunline;


import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: liyuan  liyuan1@sunline.cn
 * @date: 2019/2/27 15:44
 * @description: RSA工具类
 * @version: 4.0
 **/
public class RSAUtil {

    /* UseRSAUtil 相关*/
    public static final String USE = "RSA";
    //公钥
    public static final String PUBLIC_KEY = "RSAPublicKey";
    //私钥
    public static final String PRIVATE_KEY = "RSAPrivateKey";
    // 加验签算法选择 可选
    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
    // RSA最大加密明文大小
    private static final int MAX_ENCRYPT_BLOCK = 117;
    // RSA私钥解密最大密文大小
    private static final int  MAX_DECRYPT_PRI = 256;

    //---------------------------RSA公私钥生成-----------------------------------------

    /* 公私钥map*/
    private static Map<String, Object> keyMap = new HashMap();

    /**
     * 公私钥初始化
     *
     * @return keyMap
     */
    @Deprecated
    public static Map<String, Object> getKeyMap() throws NoSuchAlgorithmException {
        //1、构建公私钥匙对。  NoSuchAlgorithmException
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(USE);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //2、拿到公钥。
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //3、拿到私钥。
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 通过map取得私钥字符串
     *
     * @param keyMap 密钥map
     * @return byte[] 私钥
     */
    @Deprecated
    public static byte[] getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 通过map取得公钥字符串
     *
     * @param keyMap 密钥map
     * @return byte[] 公钥
     */
    @Deprecated
    public static byte[] getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }

    /**
     * 根据公钥生成公钥对象
     *
     * @param key
     * @return PublicKey
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = android.util.Base64.decode(key, android.util.Base64.DEFAULT);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(USE);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 根据私钥生成私钥对象
     *
     * @param key
     * @return PrivateKey
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = android.util.Base64.decode(key, android.util.Base64.DEFAULT);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(USE);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    // ----------------------------RSA 加验签----------------------------

    /**
     * 私钥加签
     *
     * @param data
     * @return 经过base64编码的签名
     * @throws Exception
     */
    public static String sign(String privateKey, byte[] data) throws Exception {
        PrivateKey priK = getPrivateKey(privateKey);
        Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initSign(priK);
        sig.update(data);
        byte[] afterSign = sig.sign();
        // 返回base64编码签名转成string
        return Base64.encode(afterSign);
    }

    /**
     * 公钥验签
     *
     * @param publicKey 公钥字符串
     * @param data      报文
     * @param sign      需要使用base64解码的签名
     * @return true or false
     * @throws Exception
     */
    public static boolean verify(String publicKey, byte[] data, String sign) throws Exception {
        // 使用base64解码签名转成byte[]
        byte[] beformVerify = Base64.decode(sign);
        PublicKey pubK = getPublicKey(publicKey);
        Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initVerify(pubK);
        sig.update(data);
        return sig.verify(beformVerify);
    }


    // ---------------------------- RSA 加解密----------------------------

    /**
     * 公钥加密
     *
     * @param waitEncrypt
     * @return byte[]
     * @throws Exception
     */
    public static String encrypt(String publicKeyFrom, byte[] waitEncrypt) throws Exception {
        String publicK = publicKeyFrom.replaceAll("\\r\\n", "");
        PublicKey publicKey = getPublicKey(publicK);
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = waitEncrypt.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(waitEncrypt, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(waitEncrypt, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64.encode(encryptedData);
    }


    /**
     * 私钥解密
     *
     * @param waitDecrypt
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decrypt(String privateK, String waitDecrypt) throws Exception {
        byte[] data = Base64.decode(waitDecrypt);
        PrivateKey privateKey = getPrivateKey(privateK.replaceAll("\\r\\n", ""));
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        for (int i = 0; inputLen - offSet > 0; offSet = i * MAX_DECRYPT_PRI) {
            byte[] cache;
            if (inputLen - offSet > MAX_DECRYPT_PRI) {
                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_PRI);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }
}
