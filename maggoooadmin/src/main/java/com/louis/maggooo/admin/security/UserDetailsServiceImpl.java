package com.louis.maggooo.admin.security;

import com.alibaba.druid.util.StringUtils;
import com.louis.maggooo.admin.dao.SysMenuMapper;
import com.louis.maggooo.admin.dao.SysRoleMenuMapper;
import com.louis.maggooo.admin.dao.SysUserMapper;
import com.louis.maggooo.admin.dao.SysUserRoleMapper;
import com.louis.maggooo.admin.example.SysMenuExample;
import com.louis.maggooo.admin.example.SysRoleMenuExample;
import com.louis.maggooo.admin.example.SysUserExample;
import com.louis.maggooo.admin.example.SysUserRoleExample;
import com.louis.maggooo.admin.model.SysMenu;
import com.louis.maggooo.admin.model.SysRoleMenu;
import com.louis.maggooo.admin.model.SysUser;
import com.louis.maggooo.admin.model.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {

        SysUserExample sysUserExample=new SysUserExample();
        sysUserExample.createCriteria().andNameEqualTo(username);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if(CollectionUtils.isEmpty(sysUsers)){
            throw new UsernameNotFoundException("该用户不存在");
        }
        SysUser sysUser=sysUsers.get(0);

        //查询用户角色表
        SysUserRoleExample sysUserRoleExample=new SysUserRoleExample();
        sysUserRoleExample.createCriteria().andUserIdEqualTo(sysUser.getId());
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(sysUserRoleExample);
        if(CollectionUtils.isEmpty(sysUserRoles)){
            throw new UsernameNotFoundException("用户角色不存在");
        }
        SysUserRole sysUserRole=sysUserRoles.get(0);

        //查询角色菜单表
        SysRoleMenuExample sysRoleMenuExample=new SysRoleMenuExample();
        sysRoleMenuExample.createCriteria().andRoleIdEqualTo(sysUserRole.getRoleId());
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectByExample(sysRoleMenuExample);
        if(CollectionUtils.isEmpty(sysRoleMenus)){
            throw new UsernameNotFoundException("角色菜单不存在");
        }
        //获取menusIds
        List<Long> menusIds = sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());

        //查询菜单表获取权限
        SysMenuExample sysMenuExample=new SysMenuExample();
        sysMenuExample.createCriteria().andIdIn(menusIds);
        List<SysMenu> sysMenus = sysMenuMapper.selectByExample(sysMenuExample);

        Set<String> perms=new HashSet<>();
        for (SysMenu sysMenu : sysMenus) {
            if(!StringUtils.isEmpty(sysMenu.getPerms())){

                perms.add(sysMenu.getPerms());
            }
        }

        List<GrantedAuthorityImpl> grantedAuthorities = perms.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());

        return new JwtUserDetails(sysUser.getName(),sysUser.getPassword(),sysUser.getSalt(),grantedAuthorities);


    }
}
