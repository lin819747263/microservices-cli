package com.linmsen.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linmsen.core.vo.CommonResult;
import com.linmsen.core.vo.PageResult;
import com.linmsen.user.dao.SysRoleMenuMapper;
import com.linmsen.user.dao.SysUserMapper;
import com.linmsen.user.model.SysRole;
import com.linmsen.user.model.SysRoleUser;
import com.linmsen.user.model.SysUser;
import com.linmsen.user.service.SysRoleUserService;
import com.linmsen.user.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final static String LOCK_KEY_USERNAME = "username:";

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Resource
    private SysRoleUserService roleUserService;

    @Resource
    private SysRoleMenuMapper roleMenuMapper;

    @Override
    public SysUser selectByUsername(String username) {
        List<SysUser> users = baseMapper.selectList(
                new QueryWrapper<SysUser>().eq("username", username)
        );
        return getUser(users);
    }

    @Override
    public SysUser selectByMobile(String mobile) {
        List<SysUser> users = baseMapper.selectList(
                new QueryWrapper<SysUser>().eq("mobile", mobile)
        );
        return getUser(users);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void setRoleToUser(Long id, Set<Long> roleIds) {
        SysUser sysUser = baseMapper.selectById(id);
        if (sysUser == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        roleUserService.deleteUserRole(id, null);
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<SysRoleUser> roleUsers = new ArrayList<>(roleIds.size());
            roleIds.forEach(roleId -> roleUsers.add(new SysRoleUser(id, roleId)));
            roleUserService.saveBatch(roleUsers);
        }
    }

    @Override
    public CommonResult updatePassword(Long id, String oldPassword, String newPassword) {
        SysUser sysUser = baseMapper.selectById(id);
        if (StrUtil.isNotBlank(oldPassword)) {
            if (!oldPassword.equals(sysUser.getPassword())) {
                return CommonResult.error(1, "旧密码错误");
            }
        }
        if (StrUtil.isBlank(newPassword)) {
            newPassword = "123456";
        }
        SysUser user = new SysUser();
        user.setId(id);
        user.setPassword(newPassword);
        baseMapper.updateById(user);
        return CommonResult.success("修改成功");
    }

    @Override
    public List<SysRole> findRolesByUserId(Long userId) {
        return roleUserService.findRolesByUserId(userId);
    }

    @Override
    public CommonResult updateEnabled(Map<String, Object> params) {
        Long id = MapUtils.getLong(params, "id");
        Boolean enabled = MapUtils.getBoolean(params, "enabled");

        SysUser appUser = baseMapper.selectById(id);
        if (appUser == null) {
            return CommonResult.error(1, "旧密码错误");
        }
        appUser.setEnabled(enabled);
        appUser.setUpdateTime(new Date());

        int i = baseMapper.updateById(appUser);
        log.info("修改用户：{}", appUser);

        return i > 0 ? CommonResult.success(appUser) : CommonResult.error(1, "更新失败");
    }

    @Override
    public CommonResult saveOrUpdateUser(SysUser sysUser) throws Exception {
        boolean result;
        if (sysUser.getId() == null) {
            if (StringUtils.isBlank(sysUser.getType())) {
                sysUser.setType("1");
            }
            sysUser.setPassword("123456");
            sysUser.setEnabled(Boolean.TRUE);
            result = save(sysUser);
        }

        SysUser user = baseMapper.selectById(sysUser.getId());
        user.setUsername(sysUser.getUsername());
        user.setNickname(sysUser.getNickname());
        user.setMobile(sysUser.getMobile());
        user.setRoleId(sysUser.getRoleId());
        user.setSex(sysUser.getSex());
        result = saveOrUpdate(user);

        //更新角色
        if (result && StrUtil.isNotEmpty(sysUser.getRoleId())) {
            roleUserService.deleteUserRole(sysUser.getId(), null);
            List roleIds = Arrays.asList(sysUser.getRoleId().split(","));
            if (!CollectionUtils.isEmpty(roleIds)) {
                List<SysRoleUser> roleUsers = new ArrayList<>(roleIds.size());
                roleIds.forEach(roleId -> roleUsers.add(new SysRoleUser(sysUser.getId(), Long.parseLong(roleId.toString()))));
                roleUserService.saveBatch(roleUsers);
            }
        }
        return result ? CommonResult.success(sysUser) : CommonResult.error(1, "操作失败");
    }

    @Override
    public boolean delUser(Long id) {
        roleUserService.deleteUserRole(id, null);
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public PageResult<SysUser> findUsers(Map<String, Object> params) {
        Page<SysUser> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<SysUser> list = baseMapper.findList(page, params);
        long total = page.getTotal();
        if (total > 0) {
            List<Long> userIds = list.stream().map(SysUser::getId).collect(Collectors.toList());

            List<SysRole> sysRoles = roleUserService.findRolesByUserIds(userIds);
            list.forEach(u -> u.setRoles(sysRoles.stream().filter(r -> !ObjectUtils.notEqual(u.getId(), r.getUserId())).collect(Collectors.toList())));
        }
        return PageResult.<SysUser>builder().list(list).total(total).build();
    }

    private SysUser getUser(List<SysUser> users) {
        SysUser user = null;
        if (users != null && !users.isEmpty()) {
            user = users.get(0);
        }
        return user;
    }
}
