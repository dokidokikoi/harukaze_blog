package com.harukaze.blog.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.blog.app.entity.RoleEntity;
import com.harukaze.blog.app.service.RoleService;
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

import com.harukaze.blog.app.dao.UserRoleDao;
import com.harukaze.blog.app.entity.UserRoleEntity;
import com.harukaze.blog.app.service.UserRoleService;


@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleEntity> implements UserRoleService {

    @Autowired
    private RoleService roleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserRoleEntity> page = this.page(
                new Query<UserRoleEntity>().getPage(params),
                new QueryWrapper<UserRoleEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<RoleEntity> listRoleByUserId(Long id) {
        List<UserRoleEntity> userRoleEntities = this.baseMapper.selectList(
                new LambdaQueryWrapper<UserRoleEntity>()
                        .eq(UserRoleEntity::getUserId, id));
        return userRoleEntities.stream().map(item -> {
            return  roleService.getById(item.getRoleId());
        }).collect(Collectors.toList());
    }

}