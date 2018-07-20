package com.baizhi.yangyh.service;
import com.baizhi.yangyh.entity.PUser;
import com.baizhi.yangyh.entity.Pict;
import com.baizhi.yangyh.entity.Usersite;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
public interface PuserService {
    List<PUser> queryAllPuser(@Param("page") Integer page, @Param("rows") Integer rows);
    Integer myTotopage();//信息总条数


    void Servicedelete(PUser pUser);

    void Serviceupdate(PUser pUser);

    List<PUser> Servicefindlist();

    Integer Servicefindtime(int i);

    List<Usersite> Servicefindman();

    List<Usersite> Servicefindwoman();

    String insertAppUser(MultipartFile userTable) throws IOException;

    List<PUser> queryAllPuser();
}