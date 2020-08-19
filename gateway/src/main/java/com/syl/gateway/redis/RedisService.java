package com.syl.gateway.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @date Created on 2018/4/17 16:35
 * @function: 缓存处理类
 */

public interface RedisService {

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return 写入成功或失败
     */
    boolean save(String key, String value);

    /**
     * 根据key 获取value
     * @param key
     * @return
     */
    String getByKey(String key);

    /**
     * 写入缓存设置有效时间
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    boolean save(String key, Object value, long expireTime);

    boolean update(String key, Object value);

    /**
     *  批量删除对应的value
     * @param key
     */
    void remove(String... key);

    /**
     * 批量删除key
     * @param pattern
     */
    void removePattern(String... pattern);

    /**
     * 删除对应的value
     * @param key
     */
    void remove(String key);

    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    boolean exists(String key);

    /**
     * 读取缓存
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 哈希添加
     * @param key
     * @param hashKey
     * @param value
     */
    void hmSet(String key, Object hashKey, Object value);

    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    Object hmGet(String key, Object hashKey);

    Map<Object,Object> hmGet(String key);

    /**
     * 列表添加
     * @param key
     * @param v
     */
    void lPush(String key, Object v);

    /**
     * 列表获取
     * @param key
     * @param l
     * @param l1
     * @return
     */
    List<Object> lRange(String key, long l, long l1);

    /**
     * 集合添加
     * @param key
     * @param value
     */
    void add(String key, Object value);

    /**
     * 集合获取
     * @param key
     * @return
     */
    Set<Object> setMembers(String key);

    Set<Object> mGet(String key);

    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    void zAdd(String key, Object value, double scoure);

    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    Set<Object> rangeByScore(String key, double scoure, double scoure1);




}
