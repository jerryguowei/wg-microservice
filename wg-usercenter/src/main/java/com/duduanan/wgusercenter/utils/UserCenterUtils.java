package com.duduanan.wgusercenter.utils;

import com.duduanan.commons.entity.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class UserCenterUtils {

    public static List<SysMenu> buildTreeMenu(List<SysMenu> sysMenuList) {
        List<SysMenu> menus = new ArrayList<>();
        for(SysMenu tempMenu: sysMenuList) {
            if(tempMenu.getParentId() == 0) {
                menus.add(tempMenu);
            }
            for(SysMenu menu : sysMenuList) {
                if(menu.getParentId() == tempMenu.getId()){
                    if(tempMenu.getChildMenuList() == null){
                        tempMenu.setChildMenuList(new ArrayList<>());
                    }
                    tempMenu.getChildMenuList().add(menu);
                }
            }
        }
        return menus;
    }
}
