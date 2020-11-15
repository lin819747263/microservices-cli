package com.linmsen.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linmsen.user.model.SysMenu;
import com.linmsen.user.model.SysRoleMenu;

import java.util.List;
import java.util.Set;

public interface SysRoleMenuService extends IService<SysRoleMenu> {
    int save(Long roleId, Long menuId);

    int delete(Long roleId, Long menuId);

    List<SysMenu> findMenusByRoleIds(Set<Long> roleIds, Integer type);

    List<SysMenu> findMenusByRoleCodes(Set<String> roleCodes, Integer type);
}
