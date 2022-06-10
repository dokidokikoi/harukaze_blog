package com.harukaze.blog.app.dao;

import com.harukaze.blog.app.entity.PermissionEntity;
import com.harukaze.blog.app.entity.RolePermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:17
 */
@Mapper
public interface RolePermissionDao extends BaseMapper<RolePermissionEntity> {

    List<PermissionEntity> selectPermissionListByRoleId(@Param("id") Long id);
}
