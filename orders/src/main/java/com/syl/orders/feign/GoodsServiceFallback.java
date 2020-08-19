package com.syl.orders.feign;


import com.codingapi.txlcn.tc.support.DTXUserControls;
import com.syl.model.goods.Goods;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GoodsServiceFallback implements GoodsFeign{

    public List<Goods> get(){
        return new ArrayList<>();
    }

    @Override
    public int reduceStock(List<Goods> goodsList) {
        DTXUserControls.rollbackCurrentGroup();    //只需换一下回滚API就可以了
        return  100;
    }
}
