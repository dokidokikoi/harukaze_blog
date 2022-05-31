package com.harukaze.blog.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

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
	@TableId
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
	 * 密码
	 */
	private String password;
	/**
	 * 状态
	 */
	private Integer stats;
	/**
	 * 简介
	 */
	private String summary;

}
