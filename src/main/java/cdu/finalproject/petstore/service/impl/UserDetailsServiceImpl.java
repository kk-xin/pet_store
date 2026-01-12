package cdu.finalproject.petstore.service.impl;

import cdu.finalproject.petstore.entity.SysPermission;
import cdu.finalproject.petstore.entity.SysRole;
import cdu.finalproject.petstore.entity.SysUser;
import cdu.finalproject.petstore.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名在数据库中查找用户
        SysUser sysUser = sysUserService.findByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 添加角色
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<SysRole> sysRoles = sysUser.getRoles();
        for (SysRole sysRole : sysRoles) {
            authorities.add(new SimpleGrantedAuthority(sysRole.getCode()));
            //添加权限
            for (SysPermission sysPermission : sysRole.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(sysPermission.getCode()));
            }
        }

        //构造security框架所需的User对象
        return new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
    }

    public SysUser toSysUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return sysUserService.findByUsername(user.getUsername());
    }


}