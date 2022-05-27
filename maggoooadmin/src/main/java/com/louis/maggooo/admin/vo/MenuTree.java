package com.louis.maggooo.admin.vo;

import com.louis.maggooo.admin.model.SysMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTree {

    private SysMenu sysMenu;

    private List<MenuTree> children;
}
