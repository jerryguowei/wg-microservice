package com.duduanan.wggateway;

import com.duduanan.wggateway.filters.CustomGlobalFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Mono;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableFeignClients
//@Import(HttpMessageConvertersAutoConfiguration.class)
public class WgGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WgGatewayApplication.class, args);
    }

    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().toString());
    }

    @Bean
    public GlobalFilter customFilter(){
        return new CustomGlobalFilter();
    }

    @PreDestroy
    public void onExist() {
        System.out.println("start close application");
    }

}
