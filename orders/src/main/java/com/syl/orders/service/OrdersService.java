package com.syl.orders.service;

import com.syl.model.goods.Goods;
import com.syl.model.orders.Orders;

import java.util.List;

public interface OrdersService {


    List<Orders> get();

    int insert(Orders orders,List<Goods> goods);
}
