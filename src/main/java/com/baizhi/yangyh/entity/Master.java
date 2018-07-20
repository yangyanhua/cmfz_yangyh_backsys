package com.baizhi.yangyh.entity;

import java.io.Serializable;

public class Master implements Serializable{
	
	private String id;
	private String faname;
	private String imgsrc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFaname() {
		return faname;
	}
	public void setFaname(String faname) {
		this.faname = faname;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	@Override
	public String toString() {
		return "Master [id=" + id + ", faname=" + faname + ", imgsrc=" + imgsrc
				+ "]";
	}
	
	

}
