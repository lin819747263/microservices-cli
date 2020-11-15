package com.linmsen.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linmsen.user.model.SysRole;
import com.linmsen.user.model.SysRoleMenu;
import com.linmsen.user.model.SysRoleUser;

import java.util.List;

public interface SysRoleUserService extends IService<SysRoleUser> {

    /**
     * 删除用户角色
     * @param userId 用户id
     * @param roleId 角色id
     * @return
     */
    int deleteUserRole(Long userId, Long roleId);

    /**
     * 保存用户juese
     * @param userId 用户id
     * @param roleId 角色id
     * @return
     */
    int saveUserRoles(Long userId, Long roleId);

    /**
     * 根据用户id获取角色
     *
     * @param userId 用户id
     * @return
     */
    List<SysRole> findRolesByUserId(Long userId);

    /**
     * 根据用户ids 获取
     *
     * @param userIds
     * @return
     */
    List<SysRole> findRolesByUserIds(List<Long> userIds);
}
