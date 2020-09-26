package cn.service;

import cn.bean.Orders;


import java.util.List;

public interface OrdersService {
    public List<Orders> findAll(Integer page,Integer size);
    public Orders findById(Integer ordersId);
}
