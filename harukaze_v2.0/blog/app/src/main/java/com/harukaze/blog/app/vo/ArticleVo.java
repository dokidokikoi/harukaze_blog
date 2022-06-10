package com.harukaze.blog.app.vo;

import com.harukaze.blog.app.entity.ArticleBodyEntity;
import com.harukaze.blog.app.entity.CategoryEntity;
import com.harukaze.blog.app.entity.TagEntity;
import lombok.Data;

import java.util.List;

/**
 * @PackageName: com.harukaze.blog.app.vo
 * @ClassName: ArticleVo
 * @Description:
 * @Author: doki
 * @Date: 2022/6/7 15:51
 */

@Data
public class ArticleVo {

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
    private Integer viewCounts;
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
    private Integer state;
    /**
     * 创建时间
     */
    private Long createDate;
    /**
     * 更新时间
     */
    private Long updateDate;
    /**
     * 文章
     */
    private ArticleBodyEntity body;
    /**
     * 作者
     */
    private UserVo author;
    /**
     * 分类
     */
    private CategoryEntity category;

    private List<TagEntity> tags;
}
