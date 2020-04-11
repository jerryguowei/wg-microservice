package com.duduanan.auth.service;

import com.duduanan.auth.entity.SysUser;
import com.duduanan.auth.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SysUserDetailsService implements UserDetailsService {

    @Autowired
    SysUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = userRepository.findByUsername(username);
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
