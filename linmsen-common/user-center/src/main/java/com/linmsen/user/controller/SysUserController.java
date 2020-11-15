package com.linmsen.user.controller;

import com.linmsen.core.vo.CommonResult;
import com.linmsen.core.vo.PageResult;
import com.linmsen.user.model.SysRole;
import com.linmsen.user.model.SysUser;
import com.linmsen.user.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * 用户
 */
@Slf4j
@RestController
@Api(tags = "用户模块api")
public class SysUserController {


    @Autowired
    private SysUserService appUserService;


    @PostMapping("/users/saveOrUpdate")
    public CommonResult saveOrUpdate(@RequestBody SysUser sysUser) throws Exception {
        return appUserService.saveOrUpdateUser(sysUser);
    }

    @PutMapping("/users")
    public void updateSysUser(@RequestBody SysUser sysUser) {
        appUserService.updateById(sysUser);
    }

    @DeleteMapping(value = "/users/{id}")
    public CommonResult delete(@PathVariable Long id) {
        if (checkAdmin(id)) {
            return CommonResult.error(1,"超级管理员不能删除");
        }
        appUserService.delUser(id);
        return CommonResult.success("删除成功");
    }

    @ApiOperation(value = "用户查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", required = true, dataType = "Integer")
    })

    @GetMapping("/users")
    public PageResult<SysUser> findUsers(@RequestParam Map<String, Object> params) {
        return appUserService.findUsers(params);
    }


    @GetMapping(value = "/users/name/{username}")
    @ApiOperation(value = "根据用户名查询用户实体")
    public SysUser selectByUsername(@PathVariable String username) {
        return appUserService.selectByUsername(username);
    }

    @GetMapping(value = "/users-anon/mobile", params = "mobile")
    @ApiOperation(value = "根据手机号查询用户")
    public SysUser findByMobile(String mobile) {
        return appUserService.selectByMobile(mobile);
    }


    @GetMapping("/users/{id}")
    public SysUser findUserById(@PathVariable Long id) {
        return appUserService.getById(id);
    }


    @PostMapping("/users/{id}/roles")
    public void setRoleToUser(@PathVariable Long id, @RequestBody Set<Long> roleIds) {
        appUserService.setRoleToUser(id, roleIds);
    }

    @GetMapping("/users/{id}/roles")
    public List<SysRole> findRolesByUserId(@PathVariable Long id) {
        return appUserService.findRolesByUserId(id);
    }


    @ApiOperation(value = "修改用户状态")
    @PostMapping("/users/updateEnabled")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "enabled", value = "是否启用", required = true, dataType = "Boolean")
    })
    public CommonResult updateEnabled(@RequestParam Map<String, Object> params) {
        Long id = MapUtils.getLong(params, "id");
        if (checkAdmin(id)) {
            return CommonResult.error(1,"超级管理员不能删除");
        }
        return appUserService.updateEnabled(params);
    }


    @PutMapping(value = "/users/{id}/password")
    public CommonResult resetPassword(@PathVariable Long id) {
        if (checkAdmin(id)) {
            return CommonResult.error(1,"超级管理员不能删除");
        }
        appUserService.updatePassword(id, null, null);
        return CommonResult.success("重置成功");
    }


    @PutMapping(value = "/users/password")
    public CommonResult resetPassword(@RequestBody SysUser sysUser) {
        if (checkAdmin(sysUser.getId())) {
            return CommonResult.error(1,"超级管理员不能删除");
        }
        appUserService.updatePassword(sysUser.getId(), sysUser.getOldPassword(), sysUser.getNewPassword());
        return CommonResult.success("重置成功");
    }

    /**
     * 是否超级管理员
     */
    private boolean checkAdmin(long id) {
        return id == 1L;
    }
}
