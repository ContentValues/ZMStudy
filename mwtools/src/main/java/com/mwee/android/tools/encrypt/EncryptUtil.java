package com.mwee.android.tools.encrypt;

import android.text.TextUtils;
import android.util.Base64;

import com.mwee.android.tools.DateUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 * 美味通讯的加密算法
 *
 * @author zhangmin
 */
public class EncryptUtil {
    /***
     * 32位小写md5
     *
     * @param plainText
     * @return
     */
    public static String MD5Purity(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuilder buf = new StringBuilder("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            plainText = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return plainText.toLowerCase();
    }

    public static int GetRandom(int max, int min) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * 解密AES
     *
     * @param str 密文
     * @param key 密码
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(String str, String key) throws Exception {
        if (str == null || key == null) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        byte[] bytes = Base64.decode(str, Base64.NO_WRAP);
        bytes = cipher.doFinal(bytes);
        return ZipUtils.unGzip(new String(bytes, "utf-8"));
    }

    /**
     * 签名压缩
     *
     * @param shopguid
     * @param token
     * @param seed
     * @param data
     * @return
     * @throws Exception
     */
    public static String MwEncryptaut(String shopguid, String token, String seed, String data) throws Exception {
        if (TextUtils.isEmpty(seed)) {
            return data;
        }
        int start = GetRandom(9, 3);
        int end = GetRandom(9, 3);
//        int start = 3;
//        int end = 7;
        String keyseed = shopguid + token + seed.substring(start * end, start * end + end);
        String key = EncryptUtil.MD5Purity(keyseed);
        String encry = ZipUtils.gzip(DateUtil.getCurrentTimeInMills() + data);
        if (encry.indexOf("==") == encry.length() - 2) {
            encry = "2" + encry.substring(0, encry.indexOf("=="));
        } else if (encry.indexOf("=") == encry.length() - 1) {
            encry = "1" + encry.substring(0, encry.indexOf("="));
        } else {
            encry = "a" + encry;
        }
        String autograph = EncryptUtil.MD5Purity(encry + key);
        autograph = start + autograph + end;
        encry = autograph.toUpperCase() + encry;
        return encry;
    }

    public static String MwDecryptaut(String shopguid, String token, String seed, String data) throws Exception {
        if (data.length() < 35) {
            throw new ExcryptException(601);
        }
        String autograph = data.substring(0, 34);
        int start = Integer.parseInt(autograph.substring(0, 1));
        int end = Integer.parseInt(autograph.substring(33, 34));
        //System.out.println("start:"+start+":::end:"+end);
        String keyseed = shopguid + token + seed.substring(start * end, start * end + end);
        String key = EncryptUtil.MD5Purity(keyseed);
        autograph = autograph.substring(1, 33);
        data = data.substring(34, data.length());
        String autograph_ne = EncryptUtil.MD5Purity(data + key).toUpperCase();
        if (!autograph_ne.equals(autograph)) {
            throw new ExcryptException(602);
        }
        String vl = data.substring(0, 1);
        data = data.substring(1, data.length());
        if (vl.equals("2")) {
            data = data + "==";
        } else if (vl.equals("1")) {
            data = data + "=";
        }
        //System.out.println("data:"+data);
        //System.out.println("seed:"+seed);

        String Decryptstr = ZipUtils.unGzip(data);
//        long stl = Long.parseLong(Decryptstr.substring(0, 13));
//        long ltl = DateUtil.getCurrentTimeInMills();
//        long lp = (ltl - stl) / (1000 * 60);

//        if (Math.abs(lp) > 5) {
//            throw new ExcryptException(603);
//        }
        Decryptstr = Decryptstr.substring(13, Decryptstr.length());
        return Decryptstr;
    }

    /**
     * 解密
     *
     * @param shopguid 店号
     * @param token    令牌
     * @param seed     种子
     * @param data     密文
     * @return
     * @throws Exception
     */
    public static String MwDecrypt(String shopguid, String token, String seed, String data) throws Exception {
        if (data.length() < 35) {
            throw new Exception("601");
        }
        String autograph = data.substring(0, 34);
        int start = Integer.parseInt(autograph.substring(0, 1));
        int end = Integer.parseInt(autograph.substring(33, 34));
        //System.out.println("start:"+start+":::end:"+end);
        autograph = autograph.substring(1, 33);
        data = data.substring(34, data.length());
        String autograph_ne = EncryptUtil.MD5Purity(data).toUpperCase();
        if (!autograph_ne.equals(autograph)) {
            throw new Exception("602");
        }
        String vl = data.substring(0, 1);
        data = data.substring(1, data.length());
        if (vl.equals("2")) {
            data = data + "==";
        } else if (vl.equals("1")) {
            data = data + "=";
        }
        //System.out.println("data:"+data);
        //System.out.println("seed:"+seed);
        String keyseed = shopguid + token + seed.substring(start * end, start * end + end);
        String key = EncryptUtil.MD5Purity(keyseed);
        //System.out.println("key:"+key);
        String Decryptstr = EncryptUtil.aesDecrypt(data, key);
        long stl = Long.parseLong(Decryptstr.substring(0, 13));
        long ltl = DateUtil.getCurrentTimeInMills();
        long lp = (ltl - stl) / (1000 * 60);

        if (Math.abs(lp) > 2) {
            throw new Exception("603");
        }
        Decryptstr = Decryptstr.substring(13, Decryptstr.length());
        return Decryptstr;
    }


    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
