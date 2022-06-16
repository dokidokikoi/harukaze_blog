package com.harukaze.blog.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.harukaze.blog.app.entity.PermissionEntity;
import com.harukaze.blog.app.entity.RoleEntity;
import com.harukaze.blog.app.entity.UserRoleEntity;
import com.harukaze.blog.app.service.RolePermissionService;
import com.harukaze.blog.app.service.RoleService;
import com.harukaze.blog.app.service.UserRoleService;
import com.harukaze.blog.app.util.UserThreadLocal;
import com.harukaze.blog.app.vo.UserVo;
import com.harukaze.blog.common.constant.UserConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.Query;

import com.harukaze.blog.app.dao.UserDao;
import com.harukaze.blog.app.entity.UserEntity;
import com.harukaze.blog.app.service.UserService;
import org.springframework.transaction.annotation.Transactional;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public UserVo toVo(UserEntity user) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);

        // 查询用户角色
        List<RoleEntity> roles =  userRoleService.listRoleByUserId(userVo.getId());
        // 根据角色查询用户权限
        List<PermissionEntity> permissions = new ArrayList<>();
        for (RoleEntity role : roles) {
            List<PermissionEntity> permissionsChild = rolePermissionService.listPermissionByRoleId(role.getId());
            permissions.addAll(permissionsChild);
        }
        userVo.setRoles(roles);
        userVo.setPermissions(permissions);

        return userVo;
    }

    @Override
    public UserVo getUserVoById(Long id) {
        return toVo(this.getById(id));
    }

    @Override
    public PageUtils listUserPage(Map<String, Object> params) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        String key = (String) params.get("key");

        if (!StrUtil.isBlank(key)) {
            wrapper.and(w -> {
                w.eq(UserEntity::getAccount, key)
                        .or()
                        .like(UserEntity::getAccount, key)
                        .or()
                        .eq(UserEntity::getNickname, key)
                        .or()
                        .like(UserEntity::getNickname, key);
            });
        }

        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                wrapper
        );

        PageUtils pageUtils = new PageUtils(page);
        List<UserVo> collect = pageUtils.getList().stream()
                .map(item -> toVo((UserEntity) item))
                .collect(Collectors.toList());
        pageUtils.setList(collect);

        return pageUtils;
    }

    @Override
    public void saveUser(UserEntity user) {
        user.setId(null);
        user.setCreateDate(System.currentTimeMillis());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        this.save(user);
    }

    @Override
    public void updateUserById(UserEntity user) {
        if (user.getId() == null) {
            return;
        }
        user.setPassword(null);
        user.setCreateDate(null);
        user.setAccount(null);
        user.setState(
                user.getState() == UserConstant.Status.USER_UP.getCode() ?
                        user.getState() : UserConstant.Status.USER_DOWN.getCode());

        this.updateById(user);
    }

    @Override
    public UserVo getCurrentUser() {
        return UserThreadLocal.get();
    }

    @Override
    public void updateLastLogin(Long id) {
        this.baseMapper.update(null,
                new LambdaUpdateWrapper<UserEntity>()
                        .eq(UserEntity::getId, id)
                        .set(UserEntity::getLastLogin, System.currentTimeMillis()));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void setRoles(Long id, Long[] ids) {

        userRoleService.remove(
                new LambdaQueryWrapper<UserRoleEntity>()
                        .eq(UserRoleEntity::getUserId, id));

        List<Long> collect = roleService.list().stream()
                .map(RoleEntity::getId)
                .collect(Collectors.toList());

        for (Long aLong : ids) {
            if (collect.contains(aLong)) {
                UserRoleEntity userRoleEntity = new UserRoleEntity();
                userRoleEntity.setUserId(id);
                userRoleEntity.setRoleId(aLong);
                userRoleService.save(userRoleEntity);
            }
        }
    }

}