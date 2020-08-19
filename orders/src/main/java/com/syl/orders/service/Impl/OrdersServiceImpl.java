package com.syl.orders.service.Impl;

import com.codingapi.txlcn.tc.annotation.ITxTransaction;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxTransaction;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.syl.model.goods.Goods;
import com.syl.model.orders.Orders;
import com.syl.orders.dao.OrdersDao;
import com.syl.orders.feign.GoodsFeign;
import com.syl.orders.service.OrdersService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao mOrdersDao;

    @Autowired
    private GoodsFeign goodsFeign;

    @Override
    public List<Orders> get() {
        List<Orders> orders = mOrdersDao.get(new Orders());
        return orders;
    }

    @Override
    @LcnTransaction
    @Transactional
    public int insert(Orders orders, List<Goods> goods) {
        int insert = mOrdersDao.insert(orders);
        int i = goodsFeign.reduceStock(goods);
        System.out.println(i);
        mOrdersDao.insert(orders);
        return insert;
    }

}
