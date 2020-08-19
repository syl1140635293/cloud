package com.syl.goods.service;

import com.syl.model.goods.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> get();

    int reduceStock(List<Goods> goodsList);
}

