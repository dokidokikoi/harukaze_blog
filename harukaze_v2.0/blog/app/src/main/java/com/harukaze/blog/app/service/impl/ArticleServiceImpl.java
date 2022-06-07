package com.harukaze.blog.app.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harukaze.blog.app.entity.ArticleBodyEntity;
import com.harukaze.blog.app.service.ArticleBodyService;
import com.harukaze.blog.app.service.CategoryService;
import com.harukaze.blog.app.service.UserService;
import com.harukaze.blog.app.vo.ArticleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.Query;

import com.harukaze.blog.app.dao.ArticleDao;
import com.harukaze.blog.app.entity.ArticleEntity;
import com.harukaze.blog.app.service.ArticleService;


@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, ArticleEntity> implements ArticleService {

    @Autowired
    private ArticleBodyService articleBodyService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ArticleEntity> page = this.page(
                new Query<ArticleEntity>().getPage(params),
                new QueryWrapper<ArticleEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils listArticlePage(Map<String, Object> params) {
        // 关键字，可以是文章标题、分类名或标签名
        String key = (String) params.get("key");
        // 分类 id
        Long categoryId = null;
        try {
            categoryId = Long.parseLong((String) params.get("categoryId"));
        } catch (Exception e) {

        }
        // 标签列表
        List<Long> tags = null;
        try {
            tags = ((List<String>) params.get("categoryId")).stream()
                    .map(Long::parseLong).collect(Collectors.toList());
        } catch (Exception e) {

        }

        // 排序字段
        Map<String, String> order = (Map<String, String>) params.get("order");
        if (order == null) {
            order = new HashMap<>();
        }
        String time = order.getOrDefault("time", null);
        String view = order.getOrDefault("view", null);
        String comment = order.getOrDefault("comment", null);

        // 分页字段
        int currt = Integer.parseInt((String) params.getOrDefault("page", "1"));
        int limit = Integer.parseInt((String) params.getOrDefault("limit", "10"));

        Page<ArticleEntity> page = new Page<>(currt, limit);
        IPage<ArticleEntity> IPage = this.baseMapper.selectArticleList(
                page, key, categoryId, tags, time, view, comment);

        return new PageUtils(IPage);
    }

    @Override
    public ArticleVo getArticleDetailById(Long id) {
        ArticleVo articleVo = new ArticleVo();
        ArticleEntity articleEntity = this.baseMapper.selectById(id);
        BeanUtils.copyProperties(articleEntity, articleVo);

        articleVo.setBody(articleBodyService.getById(articleEntity.getBodyId()));
        articleVo.setCategory(categoryService.getById(articleEntity.getCategoryId()));
        articleVo.setUser(userService.toVo(userService.getById(articleEntity.getAuthorId())));
        return articleVo;
    }

}