package com.louis.maggooo.admin.controller.menu;

import com.louis.maggooo.admin.service.menu.SysMenuService;
import com.louis.maggooo.admin.vo.MenuTree;
import com.louis.maggooo.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/findMenu")
    public HttpResult findMenuTree(@RequestParam String userName){

        List<MenuTree> tree = sysMenuService.findTree(userName, 0);
        return HttpResult.ok(tree);

    }




}
