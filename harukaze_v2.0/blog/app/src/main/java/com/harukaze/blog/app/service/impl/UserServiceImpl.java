package com.harukaze.blog.app.service.impl;

import com.harukaze.blog.app.entity.PermissionEntity;
import com.harukaze.blog.app.entity.RoleEntity;
import com.harukaze.blog.app.service.RolePermissionService;
import com.harukaze.blog.app.service.RoleService;
import com.harukaze.blog.app.service.UserRoleService;
import com.harukaze.blog.app.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.Query;

import com.harukaze.blog.app.dao.UserDao;
import com.harukaze.blog.app.entity.UserEntity;
import com.harukaze.blog.app.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

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

}