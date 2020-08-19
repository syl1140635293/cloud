package com.syl.goods.controller;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.syl.model.goods.Goods;
import com.syl.goods.service.GoodsService;
import com.syl.vo.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import  org.apache.ibatis.binding.MapperMethod;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    
    @Autowired
    private GoodsService mGoodsService;

    @GetMapping("/get")
    public Object get(){
        List<Goods> result = mGoodsService.get();
        return result;
    }


    @PostMapping("reduceStock")
    public int reduceStock(@RequestBody List<Goods> goodsList){
        int i = mGoodsService.reduceStock(goodsList);
        return 2;
    }

    @GetMapping("/test")
    public String getTest(){
        Test test = new Test();
        test.setDes("desc");
        test.setId(12L);
        test.setText("texttexttexttexttexttexttexttexttexttext");
        test.setUuid("123fasdfas");
        return test.toString();
    }
}
