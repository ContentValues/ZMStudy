package xdroid.mwee.com.mwbase.sunline;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * @Author chenqx
 * @Date 2018/8/20 14:08
 */
public class AESUtil {

    private final static String ALGORITHM = "AES";

    private AESUtil(){
    }

    /**
     * 加密
     *
     * @param plainText 明文字符串
     * @param seed      种子
     * @return 密文字节数组
     */
    public static byte[] encrypt(String plainText, byte[] seed) throws Exception {
        byte[] secretKey = geneKey(seed);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(plainText.getBytes());
    }

    /**
     * 解密
     *
     * @param encrypted 密文字节数组
     * @param seed      种子
     * @return 解密后的字符串
     */
    public static String decrypt(byte[] encrypted, byte[] seed) throws Exception {
        byte[] secretKey = geneKey(seed);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return new String(cipher.doFinal(encrypted));
    }

    /**
     * @param
     * @return 密钥数据
     */
    private static byte[] geneKey(byte[] seed) throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG","Crypto");
        secureRandom.setSeed(seed);
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        return secretKey.getEncoded();
    }
}
