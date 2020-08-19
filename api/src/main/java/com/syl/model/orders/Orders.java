package com.syl.model.orders;

import lombok.Data;

import java.util.Date;

@Data
public class Orders {
    private Long ordersId;

    private String ordersCode;

    private Long customerId;

    private String customerName;

    private Integer goodsCount;

    private Long goodsTotalPrice;

    private Long totalPrice;

    private Integer isPay;

    private Integer isDiscount;

    private Long discount;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    private Integer isDelete;

    private String remarks;

}