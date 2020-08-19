package com.syl.gateway.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.syl.gateway.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    public static final String GATEWAY_ROUTES = "gateway_dynamic_route";

    @Resource
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisService RedisService;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitions =  new ArrayList<>();
        redisTemplate.opsForHash().values(GATEWAY_ROUTES).stream().forEach(
                routeDefinition ->
                    routeDefinitions.add(JSON.parseObject(routeDefinition.toString(),RouteDefinition.class)));
        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
           redisTemplate.opsForHash().put(GATEWAY_ROUTES,routeDefinition.getId(), JSONObject.toJSONString(routeDefinition));
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            if(redisTemplate.opsForHash().hasKey(GATEWAY_ROUTES,id)){
                redisTemplate.opsForHash().delete(GATEWAY_ROUTES,id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(new NotFoundException(" route definition is not found ,routId:"+routeId)));
        });
    }




}
