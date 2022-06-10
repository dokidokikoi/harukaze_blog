package com.harukaze.blog.app.param;

import com.harukaze.blog.app.entity.ArticleBodyEntity;
import com.harukaze.blog.app.entity.CategoryEntity;
import com.harukaze.blog.app.entity.TagEntity;
import com.harukaze.blog.common.valid.AddGroup;
import com.harukaze.blog.common.valid.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @PackageName: com.harukaze.blog.app.param
 * @ClassName: ArticleParam
 * @Description:
 * @Author: doki
 * @Date: 2022/6/7 15:45
 */

@Data
public class ArticleParam {
    @NotNull(message = "修改，id不能为空", groups = UpdateGroup.class)
    private Long id;
    /**
     * 标题
     */
    @NotBlank(message = "修改，title不能为空", groups = UpdateGroup.class)
    @NotBlank(message = "新增，title不能为空", groups = AddGroup.class)
    private String title;
    /**
     * 简介
     */
    @NotBlank(message = "修改，summary不能为空", groups = UpdateGroup.class)
    @NotBlank(message = "新增，summary不能为空", groups = AddGroup.class)
    private String summary;
    /**
     * 封面
     */
    private String cover;
    /**
     * 权重
     */
    private Integer weight;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 文章
     */
    @NotNull(message = "修改，body不能为空", groups = UpdateGroup.class)
    @NotNull(message = "新增，body不能为空", groups = AddGroup.class)
    private ArticleBodyEntity body;
    /**
     * 分类
     */
    @NotNull(message = "修改，category不能为空", groups = UpdateGroup.class)
    @NotNull(message = "新增，category不能为空", groups = AddGroup.class)
    private CategoryEntity category;

    @NotNull(message = "修改，id不能为空", groups = UpdateGroup.class)
    @NotNull(message = "新增，tags不能为空", groups = AddGroup.class)
    private List<TagEntity> tags;
}
