package com.duduanan.auth.feign;

import com.duduanan.commons.Constant.ServiceNameConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = ServiceNameConstant.USER_SERVICE, configuration = FeignConfiguration.class)
public interface UserCenterFeignClient {

    @GetMapping(value="/users/name/{username}")
    public Object findUserByUsername(@PathVariable("username") String username);
}
