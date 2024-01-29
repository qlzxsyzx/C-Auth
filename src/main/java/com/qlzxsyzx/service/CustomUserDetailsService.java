package com.qlzxsyzx.service;

import com.qlzxsyzx.entity.CustomUserDetails;
import com.qlzxsyzx.entity.Permission;
import com.qlzxsyzx.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    // 实现loadUserByUsername方法，从数据库中根据用户名查找用户，并返回UserDetails对象
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("c-auth loadUserByUsername start... username:{}", s);
        User user = userService.findByUsername(s);
        if (user == null) {
            log.info("c-auth loadUserByUsername fail: username not found... username:{}", s);
            throw new UsernameNotFoundException("User not found");
        }
        log.info("c-auth loadUserByUsername success: username:{}", s);
        List<Permission> permissions = permissionService.findPermissionListByRoleId(user.getRoleId());
        return new CustomUserDetails(user.getUserId(), user.getUsername(), user.getPassword(), getAuthority(permissions));
    }

    private Collection<? extends GrantedAuthority> getAuthority(List<Permission> permissions) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Permission permission : permissions) {
            if (permission != null && permission.getPermissionName() != null) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));
            }
        }
        return grantedAuthorities;
    }
}
