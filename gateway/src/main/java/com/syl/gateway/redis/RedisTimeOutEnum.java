/**
 * Company
 * Copyright (C) 2018 All Rights Reserved.
 */
package com.syl.gateway.redis;

import lombok.Getter;

/**
 * @author SYL
 * @date Created on 2018/4/26 16:10
 * @function:
 */
@Getter
public enum RedisTimeOutEnum {
    /**
     * 100s
     */
    LOGIN_OUT_TIME(100L),
    /**
     * 10分钟过期时间
     */
    TEN_MINUTE(600L),
    /**
     * 一天
     */
    ONE_DAY(24*60*60L),

    WEEK_DAY(7*24*60*60L),

    /**
     * 一年
     */
    ONE_YEAR(365*24*60*60L);
    private Long time;
    RedisTimeOutEnum(Long time) {
        this.time = time;
    }
}
