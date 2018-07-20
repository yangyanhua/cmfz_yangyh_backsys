package com.baizhi.yangyh.service;
import com.baizhi.yangyh.dao.MenuDao;
import com.baizhi.yangyh.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional
public class MenusServiceImpl implements MenusService {
   @Autowired
   private MenuDao menuDao;
    @Override
    public List<Menu> queryMenus() {
       List<Menu> list=menuDao.selectMenus();
        return list;
    }
}
