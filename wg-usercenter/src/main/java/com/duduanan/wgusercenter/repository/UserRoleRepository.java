package com.duduanan.wgusercenter.repository;

import com.duduanan.commons.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    UserRole findUserRoleByRoleId(Integer roleId);

    Page<UserRole> findAll(Pageable pageable);

}
