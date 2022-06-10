package com.harukaze.blog.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.blog.app.entity.ArticleEntity;
import com.harukaze.blog.app.entity.TagEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    @Override
    public void delTagByArticleId(Long id) {
        this.baseMapper.delete(
                new LambdaQueryWrapper<ArticleTagEntity>()
                        .eq(ArticleTagEntity::getArticleId, id));
    }

    @Override
    public List<TagEntity> getTagsByArticleId(Long id) {

        return this.baseMapper.selectTagListByArticleId(id);
    }

    @Override
    public Map<String, Integer> getData(Long id) {
        List<ArticleEntity> articleEntities = this.baseMapper.selectArticleListByTagId(id);
        Map<String, Integer> data = new HashMap<>();

        int viewCount = 0, commentCount = 0;
        for (ArticleEntity articleEntity : articleEntities) {
            viewCount += articleEntity.getViewCounts();
            commentCount += articleEntity.getCommentCounts();
        }

        data.put("articleCount", articleEntities.size());
        data.put("viewCount", viewCount);
        data.put("commentCount", commentCount);
        return data;
    }

}