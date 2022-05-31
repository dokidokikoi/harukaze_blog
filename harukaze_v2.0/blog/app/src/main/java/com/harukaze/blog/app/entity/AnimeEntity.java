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
 * @date 2022-05-31 10:19:36
 */
@Data
@TableName("hk_anime")
public class AnimeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 动画名
	 */
	private String name;
	/**
	 * 封面url
	 */
	private String avatar;
	/**
	 * 简介
	 */
	private String summary;
	/**
	 * 网址
	 */
	private String url;

}
