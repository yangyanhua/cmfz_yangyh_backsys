package com.baizhi.yangyh.controller;
import com.baizhi.yangyh.entity.Article;
import com.baizhi.yangyh.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Administrator
 * \* Date: 2018/7/11
 * \* Time: 12:25
 * \* To change this template use File | Settings | File Templates.

 * \
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 分页查询所有轮播图信息
     * @return
     */
    @RequestMapping(value = "/queryAllArticle.do")
    @ResponseBody
    public Map<String,Object> queryAllArticle(Model model, HttpSession session,
                                              Integer page,Integer rows,Integer mid){
        System.out.println( page+"pag+++++++++++++");
        System.out.println( rows+"rows++++++++");
        System.out.println( mid+"mid++++++++++");
        List<Article> queryAllArticle = articleService.queryAllArticle(page,rows,mid);//传递当前页和每页需要遍历的条数
        Integer totopage = articleService.myTotopage();//传递总页码
        session.setAttribute("queryAllArticle",queryAllArticle);//将集合存入session中
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("total",totopage);//将总页码加入map中
        map.put("rows",queryAllArticle);//集合也放入map中
        System.out.println("map =     "+map);
        return map;
    }







    /**

     * @param aid
     */
    @RequestMapping(value = "/deleteArticle.do")
    @ResponseBody
    public String deleteArticle(Integer aid){
        System.out.println("进入删除程序++++++++++++++++");
        articleService.removeArticle(aid);
        System.out.println(aid);
        System.out.println("进入删除程序2222++++++++++++++++");
        return "success";
    }
}


