package com.syl.goods.dao;

import com.syl.model.goods.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsDao {

    int insert(Goods goods);

    int update(Goods goods);

    List<Goods> get(Goods goods);
}