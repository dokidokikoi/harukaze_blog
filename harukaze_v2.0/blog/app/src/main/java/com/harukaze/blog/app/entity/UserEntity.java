package com.harukaze.blog.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.harukaze.blog.common.valid.AddGroup;
import com.harukaze.blog.common.valid.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * 
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:17
 */
@Data
@TableName("hk_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	@NotNull(message = "更新，id不能为空", groups = UpdateGroup.class)
	private Long id;
	/**
	 * 账号
	 */
	@NotBlank(message = "修改，account不能为空", groups = AddGroup.class)
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
	@NotBlank(message = "新增，nickname不能为空", groups = AddGroup.class)
	private String nickname;
	/**
	 * 密码
	 */
	@NotBlank(message = "新增，password不能为空", groups = AddGroup.class)
	private String password;
	/**
	 * 状态
	 */
	private Integer state;
	/**
	 * 简介
	 */
	private String summary;

}
