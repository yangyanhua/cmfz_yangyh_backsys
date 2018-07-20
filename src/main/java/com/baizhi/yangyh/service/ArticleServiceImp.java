package com.baizhi.yangyh.service;
import com.baizhi.yangyh.dao.ArticleDao;
import com.baizhi.yangyh.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Administrator
 * \* Date: 2018/7/11
 * \* Time: 12:22
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 轮播图Service
 * \
 */
@Service
@Transactional
public class ArticleServiceImp implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    /**
     * 分页查询所有信息
     * @param page
     * @param rows
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Article> queryAllArticle(Integer page, Integer rows,Integer mid) {
        //当前页码为1时,计算出begin和end
        Integer begin = (page-1)*rows;//page=当前页,rows=展示的条数
        Integer end = rows;
        return articleDao.selectAll(begin,end,mid);
    }



    /**
     * 展示所有条数
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer myTotopage() {
        return articleDao.countDAO();
    }
    @Override
    public void removeArticle(@Param("aid") Integer aid){
        articleDao.delArticle(aid);

    };

}