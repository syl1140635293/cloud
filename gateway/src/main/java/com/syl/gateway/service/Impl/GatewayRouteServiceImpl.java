package com.syl.gateway.service.Impl;

import com.syl.gateway.dao.GatewayRouteDao;
import com.syl.gateway.model.GatewayRoute;
import com.syl.gateway.service.GatewayRouteService;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GatewayRouteServiceImpl implements GatewayRouteService {

    public static final String GATEWAY_ROUTES = "gateway_dynamic_route";

    @Resource
    private StringRedisTemplate redisTemplate;

    @Autowired
    private GatewayDynamicRouteService mGatewayDynamicRouteService;

    @Autowired
    private GatewayRouteDao mGatewayRouteDao;

    @Override
    public void RefreshRoute() {
        List<GatewayRoute> list = mGatewayRouteDao.getList(new GatewayRoute());
        for (GatewayRoute ga:list) {
            RouteDefinition entity =  new RouteDefinition();
            entity.setId(ga.getServiceId());
            entity.setUri(URI.create(ga.getUri()));
            entity.setOrder(Integer.valueOf(ga.getOrder()));
            List<PredicateDefinition> predicates = new ArrayList<>();
            PredicateDefinition pre =  new PredicateDefinition();
            pre.setName("Path");
            Map map = new HashMap();
            map.put("_genkey_0",ga.getPredicates());
            pre.setArgs(map);
            predicates.add(pre);
            entity.setPredicates(predicates);
            List<FilterDefinition> filters = new ArrayList<>();
            entity.setFilters(filters);


            boolean b = this.hasKey(ga.getServiceId());
            if(b){
                mGatewayDynamicRouteService.update(entity);
            }else{
                mGatewayDynamicRouteService.add(entity);
            }

        }
    }

    public boolean hasKey(String id){
        Boolean aBoolean = redisTemplate.opsForHash().hasKey(GATEWAY_ROUTES, id);
        return aBoolean;
    }
}
