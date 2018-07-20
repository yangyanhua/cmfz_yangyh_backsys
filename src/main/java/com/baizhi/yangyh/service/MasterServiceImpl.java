package com.baizhi.yangyh.service;
import com.baizhi.yangyh.annotation.Logging;
import com.baizhi.yangyh.dao.MasterDao;
import com.baizhi.yangyh.entity.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MasterServiceImpl implements MasterService {
    @Autowired
    private MasterDao masterDao;

    @Logging(value = "查所有上师")
    @Override
    public List<Master> findlist() {
        List<Master> servicelist = masterDao.servicelist();
        return servicelist;
    }
}
