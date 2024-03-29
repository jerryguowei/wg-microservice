package com.duduanan.wgusercenter.repository;

import com.duduanan.commons.entity.SysMenu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SysMenuRepository extends CrudRepository<SysMenu, Integer> {
    List<SysMenu> findAll();
}
