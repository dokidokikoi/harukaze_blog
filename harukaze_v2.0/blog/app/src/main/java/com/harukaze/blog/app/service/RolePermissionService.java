package com.harukaze.blog.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.blog.app.entity.PermissionEntity;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.app.entity.RolePermissionEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:17
 */
public interface RolePermissionService extends IService<RolePermissionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PermissionEntity> listPermissionByRoleId(Long id);
}

