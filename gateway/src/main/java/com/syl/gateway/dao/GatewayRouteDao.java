package com.syl.gateway.dao;

import com.syl.gateway.model.GatewayRoute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GatewayRouteDao {

    List<GatewayRoute> getList(GatewayRoute record);
}