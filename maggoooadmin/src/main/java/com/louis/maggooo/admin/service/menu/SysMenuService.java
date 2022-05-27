package com.louis.maggooo.admin.service.menu;

import com.louis.maggooo.admin.vo.MenuTree;

import java.util.List;

public interface SysMenuService {

    /**
     * 查询菜单树,用户ID和用户名为空则查询全部
     *
     * @param userName
     * @param menuType 取菜单类型，0：获取所有菜单，包含按钮，1：获取所有菜单，不包含按钮
     * @return
     */
    List<MenuTree> findTree(String userName, int menuType);

}
