package cn.dao;

import java.util.List;

import cn.bean.Member;
import cn.bean.Orders;
import cn.bean.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDao {
    @Select("select *from orders")
    @Results({
            @Result(id = true,column = "id" ,property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column ="productId",javaType = Product.class, property = "product",one =@One(select = "cn.dao.ProductDao.findById"))
    })
            public List<Orders> findAll();

    @Select("select *from orders where id =#{ordersId}")
    @Results({
            @Result(id = true,column = "id" ,property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column ="productId",javaType = Product.class, property = "product",one =@One(select = "cn.dao.ProductDao.findById")),
            @Result(column ="memberId",javaType =Member.class, property = "member",one =@One(select = "cn.dao.MemberDao.findById")),
            @Result(column ="id",javaType =java.util.List.class ,property ="travellers",many =@Many(select = "cn.dao.TravellerDao.findByOrdersId"))
    })
            public Orders findById(Integer ordersId);
}
