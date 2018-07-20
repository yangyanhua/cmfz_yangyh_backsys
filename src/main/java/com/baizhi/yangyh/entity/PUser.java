package com.baizhi.yangyh.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baizhi.yangyh.annotation.AnnName;
import com.baizhi.yangyh.annotation.Content;

import java.io.Serializable;
import java.util.Date;

public class PUser implements Serializable {
    @AnnName(name = "编号")
    @Content(value = "id")
    private String id;
    @AnnName(name = "名字")
    @Content(value = "name")
    private String name;
    @AnnName(name = "法号")
    @Content(value = "fhname")
    private String fhname;
    @AnnName(name = "邮箱")
    @Content(value = "email")
    private String email;
    @AnnName(name = "手机")
    @Content(value = "phone")
    private String phone;
    @AnnName(name = "密码")
    @Content(value = "password")
    private String password;
    @AnnName(name = "性别")
    @Content(value = "sex")
    private String sex;
    @AnnName(name = "位置")
    @Content(value = "site")
    private String site;
    @AnnName(name = "签名")
    @Content(value = "signature")
    private String signature;
    @AnnName(name = "头像")
    @Content(value = "headurl")
    private String headurl;
    @AnnName(name = "状态")
    @Content(value = "state")
    private String state;
    @AnnName(name = "QQ")
    @Content(value = "qq")
    private String qq;
    @AnnName(name = "微信")
    @Content(value = "weix")
    private String weix;
    @AnnName(name = "最后一次登录时间")
    @Content(value = "lastlogtime")
    @JSONField(format = "yyyy-MM-dd ")
    private Date lastlogtime;
    @AnnName(name = "注册时间")
    @Content(value = "registtime")
    @JSONField(format = "yyyy-MM-dd")
    private Date registtime;
    //关系属性

    private Master master;

    public PUser() {
    }

    @Override
    public String toString() {
        return "PUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fhname='" + fhname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", site='" + site + '\'' +
                ", signature='" + signature + '\'' +
                ", headurl='" + headurl + '\'' +
                ", state='" + state + '\'' +
                ", qq='" + qq + '\'' +
                ", weix='" + weix + '\'' +
                ", lastlogtime=" + lastlogtime +
                ", registtime=" + registtime +
                ", master=" + master +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFhname() {
        return fhname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public String getSite() {
        return site;
    }

    public String getSignature() {
        return signature;
    }

    public String getHeadurl() {
        return headurl;
    }

    public String getState() {
        return state;
    }

    public String getQq() {
        return qq;
    }

    public String getWeix() {
        return weix;
    }

    public Date getLastlogtime() {
        return lastlogtime;
    }

    public Date getRegisttime() {
        return registtime;
    }

    public Master getMaster() {
        return master;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFhname(String fhname) {
        this.fhname = fhname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setWeix(String weix) {
        this.weix = weix;
    }

    public void setLastlogtime(Date lastlogtime) {
        this.lastlogtime = lastlogtime;
    }

    public void setRegisttime(Date registtime) {
        this.registtime = registtime;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public PUser(String id, String name, String fhname, String email, String phone, String password, String sex, String site, String signature, String headurl, String state, String qq, String weix, Date lastlogtime, Date registtime, Master master) {
        this.id = id;
        this.name = name;
        this.fhname = fhname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.sex = sex;
        this.site = site;
        this.signature = signature;
        this.headurl = headurl;
        this.state = state;
        this.qq = qq;
        this.weix = weix;
        this.lastlogtime = lastlogtime;
        this.registtime = registtime;
        this.master = master;
    }
}