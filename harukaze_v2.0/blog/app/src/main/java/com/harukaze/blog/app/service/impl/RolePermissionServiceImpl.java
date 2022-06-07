package com.harukaze.blog.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.blog.app.entity.PermissionEntity;
import com.harukaze.blog.app.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.Query;

import com.harukaze.blog.app.dao.RolePermissionDao;
import com.harukaze.blog.app.entity.RolePermissionEntity;
import com.harukaze.blog.app.service.RolePermissionService;


@Service("rolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermissionEntity> implements RolePermissionService {

    @Autowired
    private PermissionService permissionService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RolePermissionEntity> page = this.page(
                new Query<RolePermissionEntity>().getPage(params),
                new QueryWrapper<RolePermissionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<PermissionEntity> listPermissionByRoleId(Long id) {
        List<RolePermissionEntity> rolePermissionEntities = this.baseMapper.selectList(
                new LambdaQueryWrapper<RolePermissionEntity>()
                        .eq(RolePermissionEntity::getRoleId, id));
        return rolePermissionEntities.stream().map(item -> {
            return permissionService.getById(item.getPermissionId());
        }).collect(Collectors.toList());
    }

}