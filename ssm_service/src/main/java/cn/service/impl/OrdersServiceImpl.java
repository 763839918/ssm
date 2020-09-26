package cn.service.impl;

import cn.bean.Orders;
import cn.dao.OrdersDao;
import cn.service.OrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;
    @Override
    public List<Orders> findAll(Integer page,Integer size) {
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }
    @Override
    public Orders findById(Integer ordersId) {
        return ordersDao.findById(ordersId);
    }
}
