package com.baizhi.yangyh.service;

import com.baizhi.yangyh.entity.Article;
import com.baizhi.yangyh.entity.Pict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleService {
    public List<Article> queryAllArticle(Integer page, Integer rows,Integer mid);

    public Integer myTotopage();
    void removeArticle(@Param("aid") Integer aid);

}
