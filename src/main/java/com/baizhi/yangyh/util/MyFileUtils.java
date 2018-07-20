package com.baizhi.yangyh.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;

public class MyFileUtils {
	/**
	 * 把给定的文件名进行唯一的处理
	 * 
	 */
	public static String GetUniqueFileName(String oldFileName){
		//时间戳的形式唯一处理:文件名
		String prefixFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		//获得文件后缀
		String subFixFileName = FilenameUtils.getExtension(oldFileName);
		StringBuffer sb = new StringBuffer();
		//最终的文件名
		String finalFileName = sb.append(prefixFileName).append(".").append(subFixFileName).toString();
		return finalFileName;
	}
}
