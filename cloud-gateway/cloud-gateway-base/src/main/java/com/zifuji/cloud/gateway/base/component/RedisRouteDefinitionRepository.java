package com.zifuji.cloud.gateway.base.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository{


	private StringRedisTemplate stringRedisTemplate;



	@Override
	public Flux<RouteDefinition> getRouteDefinitions() {
		List<RouteDefinition> routeDefinitionList=new ArrayList<RouteDefinition>();
		stringRedisTemplate.opsForHash().values("gateway1").stream().forEach(routeDefinition->{
			RouteDefinition e=JSONObject.parseObject(JSONObject.toJSONString(routeDefinition), RouteDefinition.class);
			routeDefinitionList.add(e);
		});
		return Flux.fromIterable(routeDefinitionList);
	}

	@Override
	public Mono<Void> save(Mono<RouteDefinition> route) {
		return null;
	}

	@Override
	public Mono<Void> delete(Mono<String> routeId) {
		return null;
	}

}
