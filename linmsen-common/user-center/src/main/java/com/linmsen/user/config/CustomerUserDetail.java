package com.linmsen.user.config;

import com.linmsen.user.model.SysRole;
import com.linmsen.user.model.SysUser;
import com.linmsen.user.service.SysRoleUserService;
import com.linmsen.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class CustomerUserDetail implements UserDetailsService {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleUserService sysRoleUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        SysUser user = sysUserService.selectByUsername(username);

        // 判断用户是否存在
        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 添加权限
        List<SysRole> userRoles = sysRoleUserService.findRolesByUserId(user.getId());
        for (SysRole userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority(userRole.getName()));
        }

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
