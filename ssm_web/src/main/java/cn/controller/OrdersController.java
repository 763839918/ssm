package cn.controller;

import cn.bean.Orders;
import cn.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Name;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
   /* 未分页
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        mv.addObject("ordersList",ordersList);
        mv.setViewName("orders-list");
        return mv;
    }*/
   @RequestMapping("/findAll.do")
   public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size){
       ModelAndView mv=new ModelAndView();
       List<Orders> ordersList = ordersService.findAll(page,size);
       PageInfo pageInfo=new PageInfo(ordersList);
       mv.addObject("pageInfo",pageInfo);
       mv.setViewName("orders-page-list");
       return mv;
}
@RequestMapping("/findById")
public ModelAndView findById(@RequestParam(name = "id",required = true) int ordersId){
       ModelAndView mv =new ModelAndView();
    Orders orders = ordersService.findById(ordersId);
    mv.addObject("orders",orders);
    mv.setViewName("orders-show");
    return mv;
}

}
