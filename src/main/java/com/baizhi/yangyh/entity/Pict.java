package com.baizhi.yangyh.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Administrator
 * \* Date: 2018/7/11
 * \* Time: 12:15
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 轮播图
 * \
 */
public class Pict implements Serializable {
    private Integer pid;//轮播编号
    private String por;//轮播内容

    @JSONField(format = "yyyy-MM-dd")
    private Date ptime;//上传时间

    private String pstatus;//状态
    private String pict;//图片路径

    @JSONField(format = "yyyy-MM-dd")
    private Date utime;//修改时间

    public Pict() {
        super();
    }

    @Override
    public String toString() {
        return "Pict{" +
                "pid=" + pid +
                ", por='" + por + '\'' +
                ", ptime=" + ptime +
                ", pstatus='" + pstatus + '\'' +
                ", pict='" + pict + '\'' +
                ", utime='" + utime + '\'' +
                '}';
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPor() {
        return por;
    }

    public void setPor(String por) {
        this.por = por;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
    }

    public String getPict() {
        return pict;
    }

    public void setPict(String pict) {
        this.pict = pict;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Pict(Integer pid, String por, Date ptime, String pstatus, String pict, Date utime) {

        this.pid = pid;
        this.por = por;
        this.ptime = ptime;
        this.pstatus = pstatus;
        this.pict = pict;
        this.utime = utime;
    }
}