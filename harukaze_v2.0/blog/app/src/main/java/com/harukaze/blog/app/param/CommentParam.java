package com.harukaze.blog.app.param;

import com.harukaze.blog.common.valid.AddGroup;
import com.harukaze.blog.common.valid.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @PackageName: com.harukaze.blog.app.param
 * @ClassName: CommentParam
 * @Description:
 * @Author: doki
 * @Date: 2022/6/8 16:06
 */
@Data
public class CommentParam {
    /**
     * 评论内容
     */
    @NotBlank(message = "新增，content不能为空", groups = AddGroup.class)
    private String content;
    /**
     * 评论文章id
     */
    @NotNull(message = "新增，articleId不能为空", groups = AddGroup.class)
    private Long articleId;
    /**
     * 作者信息
     * {
     *     id: ""
     *     name: ""
     * }
     */
    @NotNull(message = "新增，author不能为空", groups = AddGroup.class)
    private Map<String, String> author;
    /**
     * 父评论id
     */
    private Long parentId;
    /**
     * 回复用户信息
     * {
     *     id: ""
     *     name: ""
     * }
     */
    private Map<String, String> toUser;
}
