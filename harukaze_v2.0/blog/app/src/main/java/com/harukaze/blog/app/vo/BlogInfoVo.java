package com.harukaze.blog.app.vo;

import lombok.Data;

/**
 * @PackageName: com.harukaze.blog.app.vo
 * @ClassName: BlogInfoVo
 * @Description:
 * @Author: doki
 * @Date: 2022/6/15 9:20
 */

@Data
public class BlogInfoVo {

    private Long id;

    private UserVo host;

    private String summary;

    private String connect;

    private String oneWord;
}
