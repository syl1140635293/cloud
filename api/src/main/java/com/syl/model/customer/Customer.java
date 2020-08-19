package com.syl.model.customer;

import lombok.Data;

import java.util.Date;

@Data
public class Customer {
    private Long customerId;

    private String customerName;

    private Integer sex;

    private String phone;

    private String password;

    private Integer totalOrder;

    private Long totalMoney;

    private Integer integral;

    private String province;

    private String city;

    private String area;

    private String areaCode;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    private Integer isDelete;

}