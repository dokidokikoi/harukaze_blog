package com.harukaze.blog.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.blog.app.param.RoleParam;
import com.harukaze.blog.app.vo.RoleVo;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.app.entity.RoleEntity;

import java.util.Map;

/**
 * 
 *
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:36
 */
public interface RoleService extends IService<RoleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils listRolePage(Map<String, Object> params);

    RoleVo getRoleById(Long id);

    void updateRole(RoleParam param);

    void saveRole(RoleEntity role);

    boolean removeRoleById(Long id);

    void setPermission(Long id, Long[] ids);
}

