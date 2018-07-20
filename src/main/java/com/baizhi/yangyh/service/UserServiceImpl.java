package com.baizhi.yangyh.service;
import com.baizhi.yangyh.dao.UserDao;
import com.baizhi.yangyh.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class UserServiceImpl implements UserService{
   @Autowired
    private UserDao userDao;
    @Transactional(propagation=Propagation.SUPPORTS)
    @Override
    public User queryUser(User user) {
      User  user1=userDao.selectUser(user);
        return user;
    }
}
