package com.syl.orders.feign;

import com.syl.model.goods.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "goods",fallback = GoodsServiceFallback.class)
public interface GoodsFeign {

    @GetMapping("/goods/get" )
    List<Goods> get();

    @PostMapping("/goods/reduceStock" )
    int reduceStock(List<Goods> goodsList);
}
