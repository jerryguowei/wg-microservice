package com.duduanan.wgusercenter.dto;

import com.duduanan.commons.entity.SysUser;

import java.util.List;
import java.util.stream.Collectors;

public class UserInfoDTO {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private List<String> userRoles;

    public UserInfoDTO() {

    }
    public UserInfoDTO(SysUser sysUser){
        this.username = sysUser.getUsername();
        this.firstName = sysUser.getFirstName();
        this.lastName = sysUser.getLastName();
        this.email = sysUser.getEmail();
        this.userRoles = sysUser.getAuthorityRoles().stream().map((role)->role.getRoleName()).collect(Collectors.toList());
        this.id = sysUser.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }
}
