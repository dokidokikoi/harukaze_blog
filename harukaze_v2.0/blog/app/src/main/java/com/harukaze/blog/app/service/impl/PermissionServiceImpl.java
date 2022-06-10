package com.harukaze.blog.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.Query;

import com.harukaze.blog.app.dao.PermissionDao;
import com.harukaze.blog.app.entity.PermissionEntity;
import com.harukaze.blog.app.service.PermissionService;


@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, PermissionEntity> implements PermissionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PermissionEntity> page = this.page(
                new Query<PermissionEntity>().getPage(params),
                new QueryWrapper<PermissionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void savePermission(PermissionEntity permission) {
        permission.setId(null);
        this.save(permission);
    }

    @Override
    public PageUtils listPermissionPage(Map<String, Object> params) {
        LambdaQueryWrapper<PermissionEntity> wrapper = new LambdaQueryWrapper<>();
        String key = (String) params.get("key");

        if (!StrUtil.isBlank(key)) {
            wrapper.like(PermissionEntity::getName, key);
        }

        IPage<PermissionEntity> page = this.page(
                new Query<PermissionEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

}