package com.harukaze.blog.app.vo;

import lombok.Data;

import java.util.List;

/**
 * @PackageName: com.harukaze.blog.app.vo
 * @ClassName: CommentVo
 * @Description:
 * @Author: doki
 * @Date: 2022/6/8 14:50
 */
@Data
public class CommentVo {

    private Long id;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论层级，最大为2
     */
    private Integer level;
    /**
     * 创建时间
     */
    private Long createDate;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 作者id
     */
    private UserVo author;
    /**
     * 回复用户id
     */
    private String toUserNickname;

    private Integer weight;

    private List<CommentVo> children;
}
