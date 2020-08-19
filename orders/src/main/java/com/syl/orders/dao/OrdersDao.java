package com.syl.orders.dao;

import com.syl.model.orders.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrdersDao {

    int insert(Orders orders);

    int update(Orders orders);

    List<Orders> get(Orders orders);
}