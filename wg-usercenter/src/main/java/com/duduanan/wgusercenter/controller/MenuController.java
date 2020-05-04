package com.duduanan.wgusercenter.controller;

import com.duduanan.commons.dto.Result;
import com.duduanan.commons.entity.SysUser;
import com.duduanan.commons.entity.UserRole;
import com.duduanan.wgusercenter.annotation.LoginUser;
import com.duduanan.commons.entity.SysMenu;
import com.duduanan.wgusercenter.repository.SysMenuRepository;
import com.duduanan.wgusercenter.repository.UserRoleRepository;
import com.duduanan.wgusercenter.services.SysMenuServices;
import com.duduanan.wgusercenter.utils.UserCenterUtils;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menus")
public class MenuController {

    private Log log = LogFactory.getLog(MenuController.class);

    @Autowired
    private SysMenuRepository sysMenuRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private SysMenuServices sysMenuServices;

    @GetMapping("/find_all")
    public List<SysMenu> findAllSysMenu() {
        List<SysMenu> list = sysMenuRepository.findAll();
        return  list;
    }

    @GetMapping("/current")
    @Transactional
    public List<SysMenu> getCurrentMenu(@LoginUser SysUser sysUser){
        if(sysUser == null || sysUser.getUsername() == null){
            throw new UsernameNotFoundException("user is not found.");
        }
        List<SysMenu> currentUserMenuList = sysUser.getAuthorityRoles().stream()
                .flatMap((auth)-> auth.getSysMenuList().stream()).collect(Collectors.toList());
        return UserCenterUtils.buildTreeMenu(currentUserMenuList);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        try {
            sysMenuRepository.deleteById(id);
            return Result.success("delete successfully.");
        } catch (Exception ex) {
            log.error("failed to delete menu: " + id, ex);
            return Result.failed("failed to delete menu");
        }
    }

    @GetMapping("/{roleId}/menus")
    public List<Map<String, Object>> findMenusByRoleId(@PathVariable Integer roleId) {

        List<SysMenu> allMenuList =  sysMenuRepository.findAll();
        List<SysMenu> roleMenuList = sysMenuServices.getMenuListByRoleIds(new HashSet<>(Arrays.asList(roleId)));

        List<Map<String, Object>> authTrees = new ArrayList<>();
        Map<Integer, SysMenu> roleMenusMap = roleMenuList.stream().collect(Collectors.toMap(SysMenu::getId, sysMenu-> sysMenu));

        for(SysMenu sysMenu : allMenuList) {
            Map<String, Object> authTree = new HashMap<>();
            authTree.put("id", sysMenu.getId());
            authTree.put("name", sysMenu.getName());
            authTree.put("parent_id", sysMenu.getParentId());
            authTree.put("open", true);
            authTree.put("checked", false);
            if(roleMenusMap.containsKey(sysMenu.getId())){
                authTree.put("checked", true);
            }
            authTrees.add(authTree);
        }
        return authTrees;
    }

}
