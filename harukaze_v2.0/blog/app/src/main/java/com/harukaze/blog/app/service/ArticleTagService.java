package com.harukaze.blog.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.blog.app.entity.TagEntity;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.app.entity.ArticleTagEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:17
 */
public interface ArticleTagService extends IService<ArticleTagEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void delTagByArticleId(Long id);

    List<TagEntity> getTagsByArticleId(Long id);

    Map<String, Integer> getData(Long id);
}

