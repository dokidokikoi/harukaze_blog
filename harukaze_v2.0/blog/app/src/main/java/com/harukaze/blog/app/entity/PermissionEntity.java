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
@TableName("hk_permission")
public class PermissionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	@NotNull(message = "修改，id不能为空", groups = UpdateGroup.class)
	private Long id;
	/**
	 * 路径
	 */
	private String path;
	/**
	 * 权限名
	 */
	@NotBlank(message = "新增，name不能为空", groups = AddGroup.class)
	@NotBlank(message = "修改，name不能为空", groups = UpdateGroup.class)
	private String name;

	private Long menuId;

}
