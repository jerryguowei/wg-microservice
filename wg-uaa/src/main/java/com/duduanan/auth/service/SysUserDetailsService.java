package com.duduanan.auth.service;

import com.duduanan.auth.feign.UserCenterFeignClient;
import com.duduanan.commons.entity.SysUser;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserDetailsService implements UserDetailsService {


    @Resource
    private UserCenterFeignClient userCenterFeignClient;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = userCenterFeignClient.findUserByUsername(username);
        if (user != null) {
            UserDetails userDetails = new SysUserDetail(user);

            if (!userDetails.isEnabled()) {
                throw new DisabledException("user is disabled.");
            }
            return userDetails;
        }
        throw new InternalAuthenticationServiceException("username and password is invalid.");
    }
}
