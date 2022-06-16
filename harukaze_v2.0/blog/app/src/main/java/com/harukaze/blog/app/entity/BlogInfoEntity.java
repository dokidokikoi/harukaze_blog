package com.harukaze.blog.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @PackageName: com.harukaze.blog.app.entity
 * @ClassName: BlogInfo
 * @Description:
 * @Author: doki
 * @Date: 2022/6/15 8:35
 */

@Data
@TableName("hk_blog_info")
public class BlogInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long host;

    private String summary;

    private String connect;

    private String oneWord;
}
