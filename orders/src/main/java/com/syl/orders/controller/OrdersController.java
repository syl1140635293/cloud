package com.syl.orders.controller;



import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.syl.model.goods.Goods;
import com.syl.model.orders.Orders;
import com.syl.orders.feign.GoodsFeign;
import com.syl.orders.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService mOrdersService;

    @Autowired
    private GoodsFeign mGoodsFeign;

    @Autowired
    private HttpServletResponse response;

    @GetMapping("/get")
    public List<Orders> get(){
        List<Orders> orders = mOrdersService.get();
        return orders;
    }


    @GetMapping("/getGoods")
    public List<Goods> getGoods(){
        List<Goods> goods = mGoodsFeign.get();
        return goods;
    }

    @PostMapping("/insert")
    public Object insert(Integer del){
        Orders orders = new Orders();
        orders.setCreateTime(new Date());
        orders.setGoodsCount(10);

        List<Goods> list =  new ArrayList();
        Goods goods =  new Goods();
        goods.setGoodsStock(1);
        goods.setGoodsId(1L);
        goods.setIsDelete(del);
        list.add(goods);
        return mOrdersService.insert(orders,list);
    }
}
