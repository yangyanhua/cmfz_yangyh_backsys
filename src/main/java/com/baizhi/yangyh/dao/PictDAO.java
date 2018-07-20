package com.baizhi.yangyh.dao;
import com.baizhi.yangyh.entity.Pict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 轮播图DAO
 */
public interface PictDAO {
    List<Pict> selectAll(@Param("begin") Integer begin, @Param("end") Integer end);//分页查询所有
    Integer countDAO();//查询总条数
    void addPict(Pict pict);//添加轮播信息
    void updatePict(Pict pict);//第一次点击删除时修改轮播图展示状态
    void delPict(@Param("pid") Integer pid);//第二次点击删除
}
