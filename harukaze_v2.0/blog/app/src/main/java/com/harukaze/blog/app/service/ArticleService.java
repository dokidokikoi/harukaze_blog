package com.harukaze.blog.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.blog.app.vo.ArticleVo;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.app.entity.ArticleEntity;

import java.util.Map;

/**
 * 
 *
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:17
 */
public interface ArticleService extends IService<ArticleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils listArticlePage(Map<String, Object> params);

    ArticleVo getArticleDetailById(Long id);
}

