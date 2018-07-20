package com.baizhi.yangyh.dao;
import com.baizhi.yangyh.entity.PUser;

import com.baizhi.yangyh.entity.Usersite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PuserDao {
   List<PUser> selectAllpage(@Param("page") Integer page, @Param("rows") Integer rows);
   List<PUser> selectAll();
   Integer countDAO();

   void delete(PUser pUser);

   List<Usersite> findman();

   List<Usersite> findwomen();

   void serviceupdate(PUser pUser);

    void addAppUser(PUser pUser);
}
