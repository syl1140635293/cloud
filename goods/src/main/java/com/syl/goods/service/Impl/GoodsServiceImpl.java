package com.syl.goods.service.Impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.codingapi.txlcn.tc.support.DTXUserControls;
import com.syl.goods.dao.GoodsDao;
import com.syl.model.goods.Goods;
import com.syl.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao mGoodsDao;

    @Override
    public List<Goods> get() {
        return mGoodsDao.get(new Goods());
    }

    @Override
    @TxcTransaction
    @Transactional
    public int reduceStock(List<Goods> goodsList) {
        for (Goods goods:goodsList) {
            Goods param =  new Goods();
            param.setGoodsId(goods.getGoodsId());
            Goods g = mGoodsDao.get(param).get(0);
            goods.setGoodsStock(g.getGoodsStock()-goods.getGoodsStock());
            goods.setState(3);
            int update2 = mGoodsDao.update(goods);
            if(goods.getIsDelete()!=null &&goods.getIsDelete()==1){
                throw new RuntimeException("手动异常 出事务");
            }
            goods.setState(2);
            int update = mGoodsDao.update(goods);
        }
        return 1;
    }
}
