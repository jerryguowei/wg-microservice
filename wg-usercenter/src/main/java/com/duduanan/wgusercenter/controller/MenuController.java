package com.duduanan.wgusercenter.controller;

import com.duduanan.wgusercenter.entity.SysMenu;
import com.duduanan.wgusercenter.repository.SysMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private SysMenuRepository sysMenuRepository;

    @GetMapping("/find_all")
    public List<SysMenu> findAllSysMenu() {
        List<SysMenu> list = sysMenuRepository.findAll();
        return  list;
    }
//
//    @GetMapping("/current")
//    public List<SysMenu>
}
