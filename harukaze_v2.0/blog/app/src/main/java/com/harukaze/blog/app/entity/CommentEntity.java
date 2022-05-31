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
@TableName("hk_comment")
public class CommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 评论层级，最大为三
	 */
	private Integer level;
	/**
	 * 创建时间
	 */
	private Long createDate;
	/**
	 * 状态
	 */
	private Integer stats;
	/**
	 * 评论文章id
	 */
	private Long articleId;
	/**
	 * 作者id
	 */
	private Long authorId;
	/**
	 * 父评论id
	 */
	private Long parentId;
	/**
	 * 回复用户id
	 */
	private Long toUserId;

}
