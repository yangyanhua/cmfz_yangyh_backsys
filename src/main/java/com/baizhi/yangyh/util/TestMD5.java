package com.baizhi.yangyh.util;

//import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class TestMD5 {
    @Test
    public void testJava() throws NoSuchAlgorithmException {
      getMd5("123456");
    }
    public String getMd5(String password){
        StringBuilder sb = new StringBuilder();
        try {
            //获取加密工具类
            MessageDigest md5 = MessageDigest.getInstance("md5");
            //调用加密方法
            byte[] digest = md5.digest("123456".getBytes());
            //看起来好看  16进制的32位字符串
            for (int i = 0; i < digest.length; i++) {
                byte b = digest[i];
                int  c = b & 0xff;
                System.out.print(c+",");
                if(c<16){//补零
                    sb.append("0");
                }
                sb.append(Integer.toHexString(c));
            }
            System.out.println(sb);
        } catch (NoSuchAlgorithmException e) {
        }
        return sb.toString();
        //e10adc3949ba59abbe56e057f20f883e
          //彩虹表
        // 123456   e10adc3949ba59abbe56e057f20f883e
        // abcd     e80b5017098950fc58aad83c8c14978e
    }
    public static String getSalt(int a){
        String s="qwerftgyuiop[]asdfghjkl;'zxcvbnm,./1234567890-=`QWERTYUIOP[]ASDFGHJKLZXCVBNM";
        int leng=s.getBytes().length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a; i++) {
             //生成随机数
            int i1 = new Random().nextInt(leng);
            String substring = s.substring(i1, i1 + 1);
            sb.append(substring);
        }
        return sb.toString();
    }
    @Test
    public void testCodec(){
        //生成随机盐
        String salt = getSalt(4);
        System.out.println(salt);
        //生成随机数
      //  String s = DigestUtils.md5Hex(salt+"123456");
        //a1ac28f9e4ac8156d11ee560fecb7318
      //  System.out.println(s);
    }
}
