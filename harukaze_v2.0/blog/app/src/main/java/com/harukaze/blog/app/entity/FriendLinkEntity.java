package com.harukaze.blog.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @date 2022-05-31 10:19:36
 */
@Data
@TableName("hk_friend_link")
public class FriendLinkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 网站名称
	 */
	private String name;
	/**
	 * 网址
	 */
	private String url;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 描述
	 */
	private String siteDesc;

	private Integer stats;

}
