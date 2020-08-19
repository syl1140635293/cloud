package com.syl.gateway.controller;

import com.syl.gateway.service.GatewayRouteService;
import com.syl.gateway.service.Impl.GatewayDynamicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gateway")
public class GatewayRouteController {

    @Autowired
    private GatewayRouteService mGatewayRouteService;

    @Autowired
    private GatewayDynamicRouteService gatewayDynamicRouteService;

    @PostMapping("add")
    public String create(@RequestBody RouteDefinition entity){
        int add = gatewayDynamicRouteService.add(entity);
        return  String.valueOf(add+"");
    }

    @PostMapping("/update")
    public String update(@RequestBody RouteDefinition entity){
        int result = gatewayDynamicRouteService.update(entity);
        return String.valueOf(result);
    }

    @GetMapping("/delete")
    public Mono<ResponseEntity<Object>> delete(String id){
        return gatewayDynamicRouteService.delete(id);
    }

    @GetMapping("addGoods")
    public String addGoods(){
        RouteDefinition entity =  new RouteDefinition();
        entity.setId("goods");
        entity.setUri(URI.create("lb://goods"));
        entity.setOrder(0);

        List<PredicateDefinition> predicates = new ArrayList<>();
        PredicateDefinition pre =  new PredicateDefinition();
        pre.setName("Path");
        Map map = new HashMap();
        map.put("_genkey_0","/goods/*");
        pre.setArgs(map);
        predicates.add(pre);
        entity.setPredicates(predicates);


        List<FilterDefinition> filters = new ArrayList<>();
        entity.setFilters(filters);

        int add = gatewayDynamicRouteService.add(entity);
        return  String.valueOf(add+"");
    }

    @GetMapping("refresh")
    public String RefreshRoute(){
        mGatewayRouteService.RefreshRoute();
        return "成功";
    }


}
