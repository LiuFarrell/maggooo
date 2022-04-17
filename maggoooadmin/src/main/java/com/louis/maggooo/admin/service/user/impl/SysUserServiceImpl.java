package com.louis.maggooo.admin.service.user.impl;

import com.louis.maggooo.admin.dao.SysUserMapper;
import com.louis.maggooo.admin.example.SysUserExample;
import com.louis.maggooo.admin.model.SysUser;
import com.louis.maggooo.admin.service.user.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findByName(String username) {

        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andNameEqualTo(username);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (CollectionUtils.isEmpty(sysUsers)) {
            //throw new UsernameNotFoundException("用户不存在");
        }

        return sysUsers.get(0);
    }


}


