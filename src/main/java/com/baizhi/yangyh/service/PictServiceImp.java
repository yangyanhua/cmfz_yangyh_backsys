package com.baizhi.yangyh.service;
import com.baizhi.yangyh.dao.PictDAO;
import com.baizhi.yangyh.entity.Pict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
public class PictServiceImp implements PictService {
    @Autowired
    private PictDAO pictDAO;

    /**
     * 分页查询所有信息
     * @param page
     * @param rows
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Pict> queryAllPict(Integer page, Integer rows) {
        //当前页码为1时,计算出begin和end
        Integer begin = (page-1)*rows;//page=当前页,rows=展示的条数
        Integer end = rows;
        return pictDAO.selectAll(begin,end);
    }

    /**
     * 展示所有条数
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer myTotopage() {
        return pictDAO.countDAO();
    }

    /**
     * 添加轮播信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addPictService(Pict pict) {
        pictDAO.addPict(pict);
    }

    /**
     * 第一次点击修改状态
     * @param pict
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePictService(Pict pict) {
            pictDAO.updatePict(pict);
    }

    /**
     * 第二次点击删除
     * @param pid
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removePict(Integer pid) {

        System.out.println("join removePict");
        pictDAO.delPict(pid);
    }
}