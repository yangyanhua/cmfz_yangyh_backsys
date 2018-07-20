package com.baizhi.yangyh.service;

import com.baizhi.yangyh.dao.MenuDao;
import com.baizhi.yangyh.dao.PuserDao;
import com.baizhi.yangyh.entity.Article;
import com.baizhi.yangyh.entity.Menu;
import com.baizhi.yangyh.entity.PUser;
import com.baizhi.yangyh.entity.User;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) // 指定spring 整合junit测试的一个类对象
@ContextConfiguration("classpath:applicationContext.xml") // 指定工厂配置文件的位置
public class Testcmfz extends TestCase {
   @Autowired
   private PictService pictService;
   @Autowired
   private ArticleService articleService;
   @Autowired
   private SqlSessionFactory sqlSessionFactory;
   @Autowired
   private PuserDao puserDao;
   @Autowired
   private MenuDao menuDao;
   @Autowired
   private PuserService puserService;
   @Autowired
   private MasterService masterService;
   @Autowired
   private MenusService menusService;
   @Autowired
   private UserService userService;
   //测试连接
   @Test
   public void testSeqsession(){
    // SqlSession seq = sqlSessionFactory.openSession();
    // System.out.println(seq);
      pictService.removePict(1);



   }

   @Test
   public void testMenu(){

    //  List<Menu> list=menusService.queryMenus();
     // System.out.println(list);
      //User user =new User("23480e57836911e89117089e011a8470","yangyanhua","yyh","123456");
      //User user1 = userService.queryUser(user);
      //System.out.println(user1);

      List<Article> list = articleService.queryAllArticle(1, 4,1);
      System.out.println(list);

   }
   @Test
   public void testPuser(){
     List<PUser> list = puserService.queryAllPuser();
    //  List<PUser> list = puserDao.selectAllpage(1, 5);
      //List<PUser> list = puserDao.selectAll();


       
      System.out.println(list);

   }

}
