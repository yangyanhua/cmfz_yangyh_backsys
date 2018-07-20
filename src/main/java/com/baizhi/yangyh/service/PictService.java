package com.baizhi.yangyh.service;
import com.baizhi.yangyh.entity.Pict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Administrator
 * \* Date: 2018/7/11
 * \* Time: 12:21
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 轮播图service
 * \
 */
public interface PictService {
    List<Pict> queryAllPict(@Param("page") Integer page, @Param("rows") Integer rows);//分页查询所有轮播信息
    Integer myTotopage();//信息总条数
    void addPictService(Pict pict);//添加轮播信息
    void updatePictService(Pict pict);//第一次删除修改状态
    void removePict(@Param("pid") Integer pid);//第二次点击删除
}