package com.harukaze.blog.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.blog.app.param.ArticleParam;
import com.harukaze.blog.app.vo.ArticleVo;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.app.entity.ArticleEntity;

import java.util.List;
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

    ArticleVo getArticleDetailById(Long id) throws Exception;

    void saveArticleDetail(ArticleParam params);

    void updateArticleById(ArticleParam params);

    void setArticleStateById(Long id, boolean flag);

    PageUtils getArticleArchives();

    List<ArticleVo> listAll();
}

