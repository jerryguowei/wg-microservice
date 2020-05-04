package com.duduanan.wgusercenter.services;

import com.duduanan.commons.dto.PageResult;
import com.duduanan.commons.entity.UserRole;
import com.duduanan.commons.utils.NumberUtils;
import com.duduanan.wgusercenter.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserRoleServices {

    @Autowired
    private UserRoleRepository userRoleRepository;

    public PageResult<UserRole> findRoles(Map<String, Object> params) {
        Integer page = NumberUtils.getInt(""+ params.get("page"));
        Integer size = NumberUtils.getInt("" + params.get("size"));

        Page<UserRole> userRoles = userRoleRepository.findAll(PageRequest.of(page, size));

        return PageResult.build(userRoles.getContent(), 200, userRoles.getContent().size());
    }
}
