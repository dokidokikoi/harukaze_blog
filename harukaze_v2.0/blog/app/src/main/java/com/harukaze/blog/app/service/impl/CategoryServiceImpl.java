package com.harukaze.blog.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.blog.app.entity.ArticleEntity;
import com.harukaze.blog.app.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.Query;

import com.harukaze.blog.app.dao.CategoryDao;
import com.harukaze.blog.app.entity.CategoryEntity;
import com.harukaze.blog.app.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils listCategoryPage(Map<String, Object> params) {
        LambdaQueryWrapper<CategoryEntity> wrapper = new LambdaQueryWrapper<>();
        String key = (String) params.get("key");

        if (!StrUtil.isBlank(key)) {
            wrapper.like(CategoryEntity::getCategoryName, key);
        }

        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public boolean removeCategoryById(Long id) {
        List<ArticleEntity> list = articleService.list(
                new LambdaQueryWrapper<ArticleEntity>()
                        .eq(ArticleEntity::getCategoryId, id));

        if (list == null || list.size() == 0) {
            this.baseMapper.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public Map<String, Integer> getCategoryData(Long id) {
        Map<String, Integer> data = new HashMap<>();
        List<ArticleEntity> list = articleService.list(
                new LambdaQueryWrapper<ArticleEntity>()
                        .eq(ArticleEntity::getCategoryId, id));
        int viewCount = 0, commentCount = 0;
        for (ArticleEntity articleEntity : list) {
            viewCount += articleEntity.getViewCounts();
            commentCount += articleEntity.getCommentCounts();
        }

        data.put("articleCount", list.size());
        data.put("viewCount", viewCount);
        data.put("commentCount", commentCount);

        return data;
    }

}