package com.harukaze.blog.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.Query;

import com.harukaze.blog.app.dao.ArticleTagDao;
import com.harukaze.blog.app.entity.ArticleTagEntity;
import com.harukaze.blog.app.service.ArticleTagService;


@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagDao, ArticleTagEntity> implements ArticleTagService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ArticleTagEntity> page = this.page(
                new Query<ArticleTagEntity>().getPage(params),
                new QueryWrapper<ArticleTagEntity>()
        );

        return new PageUtils(page);
    }

}