package com.harukaze.blog.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.Query;

import com.harukaze.blog.app.dao.ArticleBodyDao;
import com.harukaze.blog.app.entity.ArticleBodyEntity;
import com.harukaze.blog.app.service.ArticleBodyService;


@Service("articleBodyService")
public class ArticleBodyServiceImpl extends ServiceImpl<ArticleBodyDao, ArticleBodyEntity> implements ArticleBodyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ArticleBodyEntity> page = this.page(
                new Query<ArticleBodyEntity>().getPage(params),
                new QueryWrapper<ArticleBodyEntity>()
        );

        return new PageUtils(page);
    }

}