package com.github.anhTom2000.utils.encryptUtils;

import sun.misc.BASE64Encoder;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @Description : TODO   密码加密工具类
 * @Author : Weleness
 * @Date : 2020/05/03
 */
public class EncryptUtil {
    /**
     * @param password 原密码
     * @Method Description:
     * 普通MD5
     * @Author weleness
     * @Return
     */
    public static String MD5(String password) {
        if (password == null) return null;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return "JDK不支持该算法，请检查一下JDK";
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] byteArray = password.getBytes();
        byte[] digest = md5.digest(byteArray);


        StringBuilder hexValue = new StringBuilder();
        //    将加密完的字符串全部转换成0-9，a-f的字符串
        for (int i = 0; i < digest.length; i++) {
            int val = ((int) digest[i]) & 0xff;
            //    如果小于16，也就是16进制只有1位的情况下，前面补0
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }


   /**
    * @Method: generate
    * @DATE: 2020/5/6
    * @Description: TODO   动态盐值密码加密
    * @Author Weleness
    * @param password 需要加密的密码
    * @Return  java.lang.String
    */
    public static String generate(String password) {
        //        生成随机盐，长度12位
        byte[] bytes = new byte[12];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);

        StringBuilder builder = new StringBuilder();
//        将字节数组变为字符串
        for (int i = 0; i < bytes.length; i++) {
//            将生成的值，全部映射到0-255 之间
            int val = ((int) bytes[i]) & 0xff;
            if (val < 16) {
//                为了控制盐的长度，这里小于16 的值，我们将它补为 大于16的值；
//                这样，生的盐的长度是固定的：bytes * 2 ;
                builder.append(Integer.toHexString(val + 16));
            } else {
                builder.append(Integer.toHexString(val));
            }
        }

//        最终的盐，长度是 12*2 = 24 ；
        String salt = builder.toString();


//        先加盐Md5一把，再将 MD5 转换成 24位的 base64 位编码
        password = md5Hex(password + salt);

        char[] cs = new char[salt.length() + password.length()];

        for (int i = 0; i < cs.length; i += 4) {

//            密码编码
            cs[i] = password.charAt(i / 2);
            cs[i + 2] = password.charAt(i / 2 + 1);
//            盐编码
            cs[i + 1] = salt.charAt(i / 2);
            cs[i + 3] = salt.charAt(i / 2 + 1);

        }
        return new String(cs);
    }

    /**
     * @param password 原密码
     * @param md5      md5加盐后生成的密文
     * @Method Description:
     * 校验加盐后是否与原文一致
     * @Author weleness
     * @Return
     */
    public static boolean verify(String password, String md5) {
//        解码密码
        char[] cs1 = new char[24];
//        解码盐
        char[] cs2 = new char[24];
//        从MD5 中取出盐
        for (int i = 0; i < md5.length(); i += 4) {
//            取出盐
            cs2[i / 2] = md5.charAt(i + 1);
            cs2[i / 2 + 1] = md5.charAt(i + 3);
//            取出密码的MD5值（经过Base64转换后的MD5）
            cs1[i / 2] = md5.charAt(i + 0);
            cs1[i / 2 + 1] = md5.charAt(i + 2);
        }

        String salt = new String(cs2);

        return md5Hex(password + salt).equals(new String(cs1));
    }


    /**
     * @param src md5
     * @Method Description:
     * 获取十六进制字符串形式的MD5摘要
     * @Author weleness
     * @Return
     */
    private static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return Base64.getEncoder().encodeToString(bs);
        } catch (Exception e) {
            return null;
        }
    }
}
