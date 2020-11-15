package com.linmsen.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linmsen.user.model.SysMenu;

import java.util.List;
import java.util.Set;

public interface SysMenuService extends IService<SysMenu> {
    /**
     * 查询所有菜单
     */
    List<SysMenu> findAll();

    /**
     * 查询所有一级菜单
     */
    List<SysMenu> findOnes();

    /**
     * 角色分配菜单
     * @param roleId
     * @param menuIds
     */
    void setMenuToRole(Long roleId, Set<Long> menuIds);

    /**
     * 角色菜单列表
     * @param roleIds 角色ids
     * @return
     */
    List<SysMenu> findByRoles(Set<Long> roleIds);

    /**
     * 角色菜单列表
     * @param roleIds 角色ids
     * @param roleIds 是否菜单
     * @return
     */
    List<SysMenu> findByRoles(Set<Long> roleIds, Integer type);

    /**
     * 角色菜单列表
     * @param roleCodes
     * @return
     */
    List<SysMenu> findByRoleCodes(Set<String> roleCodes, Integer type);
}
