package com.linmsen.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linmsen.core.vo.CommonResult;
import com.linmsen.core.vo.PageResult;
import com.linmsen.user.model.SysRole;
import com.linmsen.user.model.SysUser;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SysUserService extends IService<SysUser> {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    SysUser selectByUsername(String username);
    /**
     * 根据手机号查询用户
     * @param mobile
     * @return
     */
    SysUser selectByMobile(String mobile);

    /**
     * 用户分配角色
     * @param id
     * @param roleIds
     */
    void setRoleToUser(Long id, Set<Long> roleIds);

    /**
     * 更新密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    CommonResult updatePassword(Long id, String oldPassword, String newPassword);

    /**
     * 用户角色列表
     * @param userId
     * @return
     */
    List<SysRole> findRolesByUserId(Long userId);

    /**
     * 状态变更
     * @param params
     * @return
     */
    CommonResult updateEnabled(Map<String, Object> params);


    /**
     *
     * @param sysUser
     * @return
     * @throws Exception
     */
    CommonResult saveOrUpdateUser(SysUser sysUser) throws Exception;

    /**
     * 删除用户
     */
    boolean delUser(Long id);

    /**
     * 用户列表
     * @param params
     * @return
     */
    PageResult<SysUser> findUsers(Map<String, Object> params);
}
