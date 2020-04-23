package com.duduanan.wgusercenter.repository;

import com.duduanan.commons.entity.SysUser;
import org.springframework.data.repository.CrudRepository;

public interface SysUserRepository extends CrudRepository<SysUser, Integer> {
    SysUser findByUsername(String username);

    SysUser save(SysUser sysUser);

    int deleteSysUsersByUsername(String username);
}
