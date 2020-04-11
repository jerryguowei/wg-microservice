package com.duduanan.auth.service;

import com.duduanan.auth.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SysUserDetail implements UserDetails {

    private static final long serialVersionUID = -7110076138946120867L;
    private String password;
    private String username;
    private boolean active;

    private List<GrantedAuthority> authorities = new ArrayList<>();

    public SysUserDetail(SysUser user) {
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.authorities = user.getAuthorityRoles().stream()
                .map(authRole -> new SimpleGrantedAuthority(authRole.getRoleName()))
                .collect(Collectors.toList());
        this.active = user.isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
