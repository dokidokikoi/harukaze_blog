package com.harukaze.blog.app.vo;

import com.harukaze.blog.app.entity.PermissionEntity;
import lombok.Data;

import java.util.List;

/**
 * @PackageName: com.harukaze.blog.app.vo
 * @ClassName: RoleVo
 * @Description:
 * @Author: doki
 * @Date: 2022/6/9 8:16
 */

@Data
public class RoleVo {
    private Long id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDesc;

    private List<PermissionEntity> permissions;
}
