package com.duduanan.wgusercenter.controller;

import com.duduanan.commons.dto.PageResult;
import com.duduanan.commons.entity.UserRole;
import com.duduanan.wgusercenter.services.UserRoleServices;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRoleController {
    private Log log = LogFactory.getLog(UserRoleController.class);

    @Autowired
    private UserRoleServices userRoleServices;

    @GetMapping("/roles")
    public PageResult<UserRole> findUserRoles(@RequestParam Map<String, Object> params) {
        return userRoleServices.findRoles(params);
    }
}
