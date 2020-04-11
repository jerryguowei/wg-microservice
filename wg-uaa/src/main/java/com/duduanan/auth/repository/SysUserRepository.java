package com.duduanan.auth.repository;

import com.duduanan.auth.entity.SysUser;
import org.springframework.data.repository.CrudRepository;

public interface SysUserRepository extends CrudRepository<SysUser, Integer> {
    SysUser findByUsername(String username);
}
