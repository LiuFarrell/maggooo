package com.louis.maggooo.admin.service.user;
import com.louis.maggooo.admin.model.SysUser;

public interface SysUserService {

    SysUser findByName(String username);
}
