package com.harukaze.blog.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.blog.app.entity.RoleEntity;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.app.entity.UserRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:17
 */
public interface UserRoleService extends IService<UserRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<RoleEntity> listRoleByUserId(Long id);
}

