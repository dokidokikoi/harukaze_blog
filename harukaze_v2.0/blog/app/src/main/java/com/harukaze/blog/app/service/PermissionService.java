package com.harukaze.blog.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.blog.app.vo.PermissionVo;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.app.entity.PermissionEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:17
 */
public interface PermissionService extends IService<PermissionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void savePermission(PermissionEntity permission);

    PageUtils listPermissionPage(Map<String, Object> params);

    List<PermissionVo> getPermissionTree();

}

