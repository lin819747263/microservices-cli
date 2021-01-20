package com.linmsen.user.controller;

import com.linmsen.core.vo.CommonResult;
import com.linmsen.core.vo.PageResult;
import com.linmsen.user.model.SysRole;
import com.linmsen.user.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * 角色管理
 */
@Slf4j
@RestController
@Api(tags = "角色模块api")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 后台管理查询角色
     * @param params
     * @return
     */
    @ApiOperation(value = "根据条件查询角色")
    @GetMapping("/roles")
    public PageResult<SysRole> findRoles(@RequestParam Map<String, Object> params) {
        return sysRoleService.findRoles(params);
    }

    /**
     * 用户管理查询所有角色
     * @return
     */
    @ApiOperation(value = "查询所有角色")
    @GetMapping("/allRoles")
    public CommonResult<List<SysRole>> findAll() {
        List<SysRole> result = sysRoleService.findAll();
        return CommonResult.success(result);
    }

    /**
     * 角色新增或者更新
     *
     * @param sysRole
     * @return
     */
    @PostMapping("/roles/saveOrUpdate")
    public CommonResult saveOrUpdate(@RequestBody SysRole sysRole) throws Exception {
        return sysRoleService.saveOrUpdateRole(sysRole);
    }

    /**
     * 后台管理删除角色
     * delete /role/1
     *
     * @param id
     */
    @ApiOperation(value = "后台管理删除角色")
    @DeleteMapping("/roles/{id}")
    public CommonResult deleteRole(@PathVariable Long id) {
        try {
            if (id == 1L) {
                return CommonResult.error(1, "管理员不可以删除");
            }
            sysRoleService.deleteRole(id);
            return CommonResult.success("操作成功");
        } catch (Exception e) {
            log.error("role-deleteRole-error", e);
            return CommonResult.error(1, "操作失败");
        }
    }
}
