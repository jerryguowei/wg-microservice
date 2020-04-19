package com.duduanan.wgusercenter.controller;

import com.duduanan.commons.entity.SysUser;
import com.duduanan.wgusercenter.repository.SysUserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private final Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    private SysUserRepository sysUserRepository;

    @GetMapping("/users/current")
    public String getLoginUserInfo(JwtAuthenticationToken jwtAuthenticationToken,@AuthenticationPrincipal Jwt jwtPrinciapl) {
        logger.info(jwtPrinciapl.getClaim("user_name"));
        return "user info";
    }

    @GetMapping(value = "/users/name/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_server')")
    public SysUser findUserByUsername(@PathVariable("username") String username){
        SysUser user = sysUserRepository.findByUsername(username);
        return user;
    }
}
