package com.baizhi.yangyh.service;

import com.baizhi.yangyh.dao.MenuDao;
import com.baizhi.yangyh.entity.Menu;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml") // 指定工厂配置文件的位置
public class UserServiceImplTest extends TestCase {

  @Autowired
  private SqlSessionFactory sqlSessionFactory;
    @Resource
    private MenuDao menuDao;
    //测试连接
    @Test
    public void testMenu( ) {
        System.out.println("nihao");
      SqlSession seq = sqlSessionFactory.openSession();
      System.out.println(seq);

    List<Menu> list=menuDao.selectMenus();
        System.out.println(list);






    }
}