package com.louis.maggooo.admin.service.menu.impl;

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
import com.louis.maggooo.admin.service.menu.SysMenuService;
import com.louis.maggooo.admin.vo.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 查询菜单树,用户ID和用户名为空则查询全部
     *
     * @param userName
     * @param menuType 取菜单类型，0：获取所有菜单，包含按钮，1：获取所有菜单，不包含按钮
     * @return
     */
    @Override
    public List<MenuTree> findTree(String userName, int menuType) {

        //根据userName查询角色
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andNameEqualTo(userName);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (CollectionUtils.isEmpty(sysUsers)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        //查询用户角色
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria().andUserIdEqualTo(sysUsers.get(0).getId());
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(sysUserRoleExample);
        if (CollectionUtils.isEmpty(sysUserRoles)) {
            throw new UsernameNotFoundException("用户角色不存在");
        }

        //查询角色菜单表
        SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
        sysRoleMenuExample.createCriteria().andRoleIdEqualTo(sysUserRoles.get(0).getRoleId());
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectByExample(sysRoleMenuExample);
        if (CollectionUtils.isEmpty(sysRoleMenus)) {
            throw new UsernameNotFoundException("用户角色菜单不存在");
        }
        List<Long> menuIds = sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());

        //查询菜单表
        SysMenuExample sysMenuExample = new SysMenuExample();
        sysMenuExample.createCriteria().andIdIn(menuIds);
        List<SysMenu> sysMenus = sysMenuMapper.selectByExample(sysMenuExample);
        List<SysMenu> menus = sysMenus.stream().sorted(Comparator.comparing(SysMenu::getOrderNum)).collect(Collectors.toList());
        //TDDO: 如果sysMenus为空时，进行处理
        List<MenuTree> menuTrees = new ArrayList<>();
        MenuTree menuTree = new MenuTree();

        List<SysMenu> tempParrent = new ArrayList<>();
        List<SysMenu> tempChildren = new ArrayList<>();

        for (SysMenu sysMenu : menus) {
            if (sysMenu.getParentId() == null || sysMenu.getParentId() == 0) {
                tempParrent.add(sysMenu);
            } else {
                tempChildren.add(sysMenu);
            }
        }
        menuTrees = getMenuTree(tempParrent, tempChildren);


        return menuTrees;
    }

    public List<MenuTree> getMenuTree(List<SysMenu> parent, List<SysMenu> children) {
        List<MenuTree> menuTrees = new ArrayList<>();

        if (CollectionUtils.isEmpty(children) || CollectionUtils.isEmpty(parent)) {
            return null;
        }
        List<SysMenu> tempParrent = new ArrayList<>();
        List<SysMenu> tempChildren = new ArrayList<>();
        for (SysMenu sysMenu : parent) {
            MenuTree menuTree = new MenuTree();
            menuTree.setSysMenu(sysMenu);
            for (SysMenu child : children) {
                if (child.getParentId() == sysMenu.getId()) {
                    tempParrent.add(child);
                } else {
                    tempChildren.add(child);
                }

            }
            if (children.size() == tempChildren.size()) {
                tempChildren.clear();
            }
            menuTree.setChildren(getMenuTree(tempParrent, tempChildren));
            menuTrees.add(menuTree);
        }
        return menuTrees;

    }

}
