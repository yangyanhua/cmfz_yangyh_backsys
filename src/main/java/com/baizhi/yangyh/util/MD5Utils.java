package com.baizhi.yangyh.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	public static String getMD5Code(String password){
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("md5");
			byte[] digest = messageDigest.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : digest) {  
				int a = b & 0xff; 
				if(a<16){
					sb.append("0");
				}
				String s = Integer.toHexString(a);
				sb.append(s);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		//加 盐  让结果咸一点
		//   
		/*用户          随机数         password
		 *  1   6yz8 0fde28c96369c4718364fc6e16ff3dc7
		 * 随机数
		 */
		//123456    e10adc3949ba59abbe56e057f20f883e
		String md5Code = MD5Utils.getMD5Code("123456"+"6yz8");
		System.out.println(md5Code);
	}
}
