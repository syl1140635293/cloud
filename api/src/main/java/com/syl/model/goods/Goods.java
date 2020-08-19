package com.syl.model.goods;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Goods {
    private Long goodsId;

    private String goodsName;

    private BigDecimal goodsPrice;

    private Integer goodsStock;

    private String goodsImg;

    private String goodsUnit;

    private String goodsCode;

    private Integer state;

    private Integer isSeckill;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    private Integer isDelete;

    private Long version;

    private String remarks;

}