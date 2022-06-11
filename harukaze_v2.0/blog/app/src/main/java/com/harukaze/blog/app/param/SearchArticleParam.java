package com.harukaze.blog.app.param;

import lombok.Data;

import java.util.List;

/**
 * @PackageName: com.harukaze.blog.app.param
 * @ClassName: SearchArticleParam
 * @Description:
 * @Author: doki
 * @Date: 2022/6/10 22:37
 */
@Data
public class SearchArticleParam {
    Long id;
    Long category;
    List<Long> tags;
    Integer limit;
    Integer page;

}
