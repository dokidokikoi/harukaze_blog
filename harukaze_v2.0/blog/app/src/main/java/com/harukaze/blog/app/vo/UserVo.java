package com.harukaze.blog.app.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.harukaze.blog.app.entity.PermissionEntity;
import com.harukaze.blog.app.entity.RoleEntity;
import lombok.Data;

import java.util.List;

/**
 * @PackageName: com.harukaze.blog.app.vo
 * @ClassName: UserVo
 * @Description:
 * @Author: doki
 * @Date: 2022/6/7 11:52
 */

@Data
public class UserVo {
    private Long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 创建时间
     */
    private Long createDate;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 最近登录时间
     */
    private Long lastLogin;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 简介
     */
    private String summary;

    private List<RoleEntity>  roles;
    private List<PermissionEntity> permissions;
}
