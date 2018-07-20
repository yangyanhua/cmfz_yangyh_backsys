package com.baizhi.yangyh.dao;
import com.baizhi.yangyh.entity.Article;
import com.baizhi.yangyh.entity.PUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {
   List<Article> selectAll(@Param("begin") Integer begin, @Param("end") Integer end,@Param("mid") Integer mid);
   Integer countDAO();//查询总条数
   void delArticle(@Param("aid") Integer pid);//第二次点击删除
}
