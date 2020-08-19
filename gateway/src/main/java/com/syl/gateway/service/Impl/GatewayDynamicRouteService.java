package com.syl.gateway.service.Impl;


import com.syl.gateway.config.RedisRouteDefinitionRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Service
public class GatewayDynamicRouteService implements ApplicationEventPublisherAware {

    @Resource
    private RedisRouteDefinitionRepository redisRouteDefinitionRepository;

    private ApplicationEventPublisher applicationEventPublisher;


    /**
     * 增加路由
     * @param routeDefinition
     * @return
     */
    public int add(RouteDefinition routeDefinition){
        redisRouteDefinitionRepository.save(Mono.just(routeDefinition)).subscribe();
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return 1;
    }

    /**
     * 更新
     * @return
     */
    public int update(RouteDefinition routeDefinition){
        redisRouteDefinitionRepository.delete(Mono.just(routeDefinition.getId()));
        redisRouteDefinitionRepository.save(Mono.just(routeDefinition)).subscribe();
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return 1;
    }

    public Mono<ResponseEntity<Object>> delete(String  id){
       return redisRouteDefinitionRepository.delete(
               Mono.just(id)).then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build())))
               .onErrorResume(t -> t instanceof NotFoundException, t ->
                Mono.just(ResponseEntity.notFound().build()));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;

    }
}
