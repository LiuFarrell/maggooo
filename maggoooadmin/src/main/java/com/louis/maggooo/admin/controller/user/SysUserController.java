package com.louis.maggooo.admin.controller.user;

import com.louis.maggooo.admin.model.SysUser;
import com.louis.maggooo.admin.service.user.SysUserService;
import com.louis.maggooo.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class SysUserController {

   @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/find",method = RequestMethod.GET)
    @ResponseBody
    //@ApiOperation(value = "用户新增",httpMethod = "POST")
    public HttpResult save(@RequestParam(value = "username") String username){
        SysUser name = sysUserService.findByName(username);
        return HttpResult.ok(name);
    }

}

