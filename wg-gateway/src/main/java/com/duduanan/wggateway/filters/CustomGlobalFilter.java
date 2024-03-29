package com.duduanan.wggateway.filters;

import com.duduanan.wggateway.feign.TokenExchangeService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.AddRequestHeaderGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private TokenExchangeService tokenExchangeService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //9abb92ac-339a-4788-815f-3894c8c27070

        List<String> authorizationHeaderList = exchange.getRequest().getHeaders().get("Authorization");
        if(authorizationHeaderList == null
                || authorizationHeaderList.isEmpty()
                || authorizationHeaderList.size() > 1
                || !authorizationHeaderList.get(0).startsWith("Bearer")
                || !authorizationHeaderList.get(0).matches("Bearer \\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}")) {
            return chain.filter(exchange);
        }
        String authHeader = authorizationHeaderList.get(0);
        String token = authHeader.replace("Bearer", "").trim();
        String jwtToken = "";
        try{
            jwtToken = tokenExchangeService.exhangeToken(token).getBody();
        } catch (FeignException ex) {
            ex.printStackTrace();
        }

        //TODO: when have exception, just return 401 at this place instead of pass to downstream services.
        ServerHttpRequest request  = exchange.getRequest().mutate().header("Authorization", "Bearer " + jwtToken).build();

        return chain.filter(exchange.mutate().request(request).build());
    }
    @Override
    public int getOrder() {
        return -1;
    }
}
