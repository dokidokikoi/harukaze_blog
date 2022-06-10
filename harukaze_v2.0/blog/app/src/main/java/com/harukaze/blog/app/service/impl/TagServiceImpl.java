package com.harukaze.blog.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.blog.app.entity.ArticleEntity;
import com.harukaze.blog.app.entity.ArticleTagEntity;
import com.harukaze.blog.app.entity.CategoryEntity;
import com.harukaze.blog.app.service.ArticleService;
import com.harukaze.blog.app.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.Query;

import com.harukaze.blog.app.dao.TagDao;
import com.harukaze.blog.app.entity.TagEntity;
import com.harukaze.blog.app.service.TagService;


@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagDao, TagEntity> implements TagService {

    @Autowired
    private ArticleTagService articleTagService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TagEntity> page = this.page(
                new Query<TagEntity>().getPage(params),
                new QueryWrapper<TagEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils listTagPage(Map<String, Object> params) {
        LambdaQueryWrapper<TagEntity> wrapper = new LambdaQueryWrapper<>();
        String key = (String) params.get("key");

        if (!StrUtil.isBlank(key)) {
            wrapper.like(TagEntity::getTagName, key);
        }

        IPage<TagEntity> page = this.page(
                new Query<TagEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public boolean removeTagById(Long id) {
        List<ArticleTagEntity> list = articleTagService.list(
                new LambdaQueryWrapper<ArticleTagEntity>()
                        .eq(ArticleTagEntity::getTagId, id));

        if (list == null || list.size() == 0) {
            this.baseMapper.deleteById(id);
            return true;
        }
        return false;
    }

}