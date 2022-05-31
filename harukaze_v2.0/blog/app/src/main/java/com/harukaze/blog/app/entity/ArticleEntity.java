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
@TableName("hk_article")
public class ArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 简介
	 */
	private String summary;
	/**
	 * 封面
	 */
	private String cover;
	/**
	 * 浏览量
	 */
	private Integer veiwCounts;
	/**
	 * 评论数
	 */
	private Integer commentCounts;
	/**
	 * 权重
	 */
	private Integer weight;
	/**
	 * 状态
	 */
	private Integer stats;
	/**
	 * 创建时间
	 */
	private Long createDate;
	/**
	 * 更新时间
	 */
	private Long updateDate;
	/**
	 * 作者
	 */
	private Long authorId;
	/**
	 * 文章
	 */
	private Long bodyId;
	/**
	 * 分类
	 */
	private Long categoryId;

}
