package com.baizhi.yangyh.entity;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {
    private String id;
    private String title;
    private String iconCls;
    private String href;
    private String pid;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    //关系属性
    private List<Menu> children;
   private Menu parent;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIconCls() {
        return iconCls;
    }

    public String getHref() {
        return href;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public Menu getParent() {
        return parent;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public Menu() {
    }

    public Menu(String id, String title, String iconCls, String href, String pid, List<Menu> children, Menu parent) {
        this.id = id;
        this.title = title;
        this.iconCls = iconCls;
        this.href = href;
        this.pid = pid;
        this.children = children;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Menu{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", iconCls='" + iconCls + '\'' + ", href='" + href + '\'' + ", pid='" + pid + '\'' + ", children=" + children + ", parent=" + parent + '}';
    }
}
