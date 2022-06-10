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
 * @date 2022-05-31 10:19:17
 */
@Data
@TableName("hk_log")
public class LogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * ip
	 */
	private Long ip;
	/**
	 * 模块
	 */
	private String module;
	/**
	 * 方法
	 */
	private String method;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 操作
	 */
	private String operation;
	/**
	 * 参数
	 */
	private String params;
	/**
	 * 耗时
	 */
	private Long time;
	/**
	 * 是否来自于我
	 */
	private Long userId;
	/**
	 * 浏览器
	 */
	private String browser;
	/**
	 * 创建时间
	 */
	private Long createDate;

	private String os;

}
