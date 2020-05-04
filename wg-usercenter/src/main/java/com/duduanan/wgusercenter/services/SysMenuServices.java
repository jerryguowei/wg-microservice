package com.duduanan.wgusercenter.services;

import com.duduanan.commons.entity.SysMenu;
import com.duduanan.commons.entity.UserRole;
import com.duduanan.wgusercenter.repository.SysMenuRepository;
import com.duduanan.wgusercenter.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysMenuServices {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Transactional
    public List<SysMenu> getMenuListByRoleIds(Set<Integer> roleIds) {
        List<UserRole> userRoleList = new ArrayList<>();
        userRoleRepository.findAllById(roleIds).forEach(userRole -> userRoleList.add(userRole));
        List<SysMenu> sysMenuList = userRoleList.stream()
                .flatMap(userRole -> userRole.getSysMenuList().stream())
                .collect(Collectors.toList());
        return sysMenuList;
    }
}
