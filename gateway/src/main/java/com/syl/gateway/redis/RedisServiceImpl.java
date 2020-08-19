package com.syl.gateway.redis;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @date Created on 2018/4/17 18:34
 * @function: redis 服务实现
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate = new RedisTemplate<String, String>();

    @Override
    public boolean save(String key, String value) {
        inits();
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getByKey(String key) {
        inits();
        try {

            return (String) redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean save(String key, Object value, long expireTime) {
        inits();
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);

            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);


            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(String key, Object value) {
        inits();
        boolean result = false;
        try {
            Long expire = redisTemplate.getExpire(key);
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }


    @Override
    public void remove(String... keys) {
        inits();
        for (String key : keys) {
            remove(key);
        }
    }

    @Override
    public void removePattern(String... pattern) {
        inits();
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    @Override
    public void remove(String key) {
        inits();
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    @Override
    public boolean exists(String key) {
        inits();
        return redisTemplate.hasKey(key);
    }

    @Override
    public Object get(String key) {
        inits();
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    public Set mGet(String key) {
        inits();
        return redisTemplate.keys(key);
    }

    @Override
    public void hmSet(String key, Object hashKey, Object value) {
        inits();
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    @Override
    public Object hmGet(String key, Object hashKey) {
        inits();
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    @Override
    public Map<Object, Object> hmGet(String key) {
        inits();
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();

        return hash.entries(key);
    }

    @Override
    public void lPush(String k, Object v) {
        inits();
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    @Override
    public List<Object> lRange(String k, long l, long l1) {
        inits();
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k, l, l1);
    }

    @Override
    public void add(String key, Object value) {
        inits();
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    @Override
    public Set<Object> setMembers(String key) {
        inits();
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    @Override
    public void zAdd(String key, Object value, double scoure) {
        inits();
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    @Override
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        inits();
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

    public void inits(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    }


}
