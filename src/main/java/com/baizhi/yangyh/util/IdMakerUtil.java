package com.baizhi.yangyh.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class IdMakerUtil {
	
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		String struuid=uuid.toString();
		struuid=struuid.replace("-", "");
		return struuid;
	}
	
	public static String getSnowID(){
		SnowflakeIdWorker idWorker = new SnowflakeIdWorker(12, 24);
		long id=idWorker.nextId();
		return id+"";
	}
	
	public static String getTimeID(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}
	
	public static String getSale(){
		return SaltUtils.getSalt(5);
	}
	
	

}
