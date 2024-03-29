package com.duduanan.wgusercenter.controller;

import com.duduanan.commons.entity.SysUser;
import com.duduanan.wgusercenter.annotation.LoginUser;
import com.duduanan.wgusercenter.dto.UserInfoDTO;
import com.duduanan.wgusercenter.repository.SysUserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;


@RestController
public class UserController {
    private final Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    private SysUserRepository sysUserRepository;

    @GetMapping("/users/current")
    @Transactional
    public UserInfoDTO getLoginUserInfo(@LoginUser SysUser sysUser) {
        if(sysUser != null) {
            return new UserInfoDTO(sysUser);
        }
        return new UserInfoDTO();
    }

    @GetMapping(value = "/users/name/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_server')")
    public SysUser findUserByUsername(@PathVariable("username") String username){
        SysUser user = sysUserRepository.findByUsername(username);
        return user;
    }
}
