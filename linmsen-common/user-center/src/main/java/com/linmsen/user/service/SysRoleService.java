package com.linmsen.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linmsen.core.vo.CommonResult;
import com.linmsen.core.vo.PageResult;
import com.linmsen.user.model.SysRole;
import com.linmsen.user.model.SysRoleMenu;

import java.util.List;
import java.util.Map;

public interface SysRoleService extends IService<SysRole> {

    void saveRole(SysRole sysRole) throws Exception;

    void deleteRole(Long id);

    /**
     * 角色列表
     * @param params
     * @return
     */
    PageResult<SysRole> findRoles(Map<String, Object> params);

    /**
     * 新增或更新角色
     * @param sysRole
     * @return Result
     */
    CommonResult saveOrUpdateRole(SysRole sysRole) throws Exception;

    /**
     * 查询所有角色
     * @return
     */
    List<SysRole> findAll();
}
