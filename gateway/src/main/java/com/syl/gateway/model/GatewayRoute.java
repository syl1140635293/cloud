package com.syl.gateway.model;

import lombok.Data;

import java.util.Date;

@Data
public class GatewayRoute {
    private Long id;

    private String serviceId;

    private String uri;

    private String predicates;

    private String filters;

    private String order;

    private String creatorId;

    private Date createDate;

    private String updateId;

    private Date updateDate;

    private String remarks;

    private String delFlag;

}