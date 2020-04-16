package com.duduanan.commons.feign;

import com.duduanan.commons.Constant.ServiceNameConstant;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = ServiceNameConstant.USER_SERVICE)
public class UserServiceFeign {
}
