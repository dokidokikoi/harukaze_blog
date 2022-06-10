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
	@NotNull(message = "修改，id不能为空", groups = UpdateGroup.class)
	private Long id;
	/**
	 * 网站名称
	 */
	@NotBlank(message = "修改，name不能为空", groups = UpdateGroup.class)
	@NotBlank(message = "新增，name不能为空", groups = AddGroup.class)
	private String name;
	/**
	 * 网址
	 */
	@NotBlank(message = "修改，url不能为空", groups = UpdateGroup.class)
	@NotBlank(message = "新增，url不能为空", groups = AddGroup.class)
	private String url;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 描述
	 */
	@NotBlank(message = "修改，siteDesc不能为空", groups = UpdateGroup.class)
	@NotBlank(message = "新增，siteDesc不能为空", groups = AddGroup.class)
	private String siteDesc;

	private Integer state;

	private Long createDate;

}
