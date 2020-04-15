package com.duduanan.wggateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-server", decode404 = true, configuration = FeignClientConfiguration.class)
public interface TokenExchangeService {

    @RequestMapping(value = "/.well-known/exchange_token")
    ResponseEntity<String> exhangeToken(@RequestParam("access_token") String accessToken);
}
