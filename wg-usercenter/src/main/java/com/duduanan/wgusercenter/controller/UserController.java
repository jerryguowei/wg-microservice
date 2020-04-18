package com.duduanan.wgusercenter.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private final Log logger = LogFactory.getLog(UserController.class);

    @GetMapping("/users/current")
    public String getLoginUserInfo(JwtAuthenticationToken jwtAuthenticationToken,@AuthenticationPrincipal Jwt jwtPrinciapl) {
        logger.info(jwtPrinciapl.getClaim("user_name"));
        return "user info";
    }

    @GetMapping("/users/name/{username}")
//    @PreAuthorize("hasAuthority('SCOPE_server')")
    public Object findUserByUsername(@PathVariable("username") String username){
        Map<String, String> user  = new HashMap<>();
        user.put("username", "jerryguowei");
        return user;
    }


}
