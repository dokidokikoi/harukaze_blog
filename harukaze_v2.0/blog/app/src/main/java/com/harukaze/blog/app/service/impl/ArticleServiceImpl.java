package com.harukaze.blog.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harukaze.blog.app.entity.*;
import com.harukaze.blog.app.handler.exception.ArticleException;
import com.harukaze.blog.app.param.ArticleParam;
import com.harukaze.blog.app.service.*;
import com.harukaze.blog.app.util.UserThreadLocal;
import com.harukaze.blog.app.vo.ArticleVo;
import com.harukaze.blog.common.constant.ArticleConstant;
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
import org.springframework.transaction.annotation.Transactional;


@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, ArticleEntity> implements ArticleService {

    @Autowired
    private ArticleBodyService articleBodyService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ThreadService threadService;

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
            categoryId = ((Integer) params.get("categoryId")).longValue();
        } catch (Exception e) {

        }
        // 标签列表
        List<Long> tags = null;
        try {
            tags = ((List<Integer>) params.get("tags")).stream()
                    .map(item -> item.longValue()).collect(Collectors.toList());
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
        int currt = (Integer) params.getOrDefault("page", 1);
        int limit = (Integer) params.getOrDefault("limit", 10);

        Page<ArticleEntity> page = new Page<>(currt, limit);
        IPage<ArticleEntity> IPage = this.baseMapper.selectArticleList(
                page, key, categoryId, tags, time, view, comment);

        // 将数据库实体类转换成前端交互数据
        PageUtils pageUtils = new PageUtils(IPage);
        List<ArticleVo> collect = pageUtils.getList().stream()
                .map(item -> toVo((ArticleEntity) item, false))
                .collect(Collectors.toList());

        pageUtils.setList(collect);

        return pageUtils;
    }

    @Override
    public ArticleVo getArticleDetailById(Long id) {
        ArticleEntity articleEntity = this.baseMapper.selectById(id);
        threadService.updateArticleViewCountById(id);
        return toVo(articleEntity, true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveArticleDetail(ArticleParam params) {
        ArticleEntity articleEntity = new ArticleEntity();
        ArticleBodyEntity articleBodyEntity = new ArticleBodyEntity();
        CategoryEntity categoryEntity = new CategoryEntity();

        // 先处理 article 的前面部分操作，之后将 category 和 body 存储在数据库后，再一次性将 article 存入数据库。
        // 最后将 tags 存入数据库
        // 注意：这里 tags 和 category 可能是新增的
        BeanUtils.copyProperties(params, articleEntity);
        articleEntity.setId(null);
        articleEntity.setCreateDate(System.currentTimeMillis());
        articleEntity.setAuthorId(UserThreadLocal.get().getId());
        articleEntity.setCommentCounts(0);
        articleEntity.setViewCounts(0);
        articleEntity.setState(ArticleConstant.Status.ACTIVE.getCode());

        articleBodyEntity.setId(null);
        articleBodyEntity.setContent(params.getBody().getContent());
        articleBodyEntity.setContentHtml(params.getBody().getContentHtml());

        // 将 body 存入数据库
        articleBodyService.save(articleBodyEntity);

        // article 设置 body id
        articleEntity.setBodyId(articleBodyEntity.getId());

        // 判断是否为新增 category
        if (params.getCategory().getId() != null && params.getCategory().getId() != 0) {
            articleEntity.setCategoryId(params.getCategory().getId());
        } else {
            categoryEntity.setId(null);
            categoryEntity.setCategoryDesc(params.getCategory().getCategoryDesc());
            categoryEntity.setCategoryName(params.getCategory().getCategoryName());
            categoryEntity.setAvatar(params.getCategory().getAvatar());

            // 将 category 存入数据库
            categoryService.save(categoryEntity);
            articleEntity.setCategoryId(categoryEntity.getId());
        }
        // 将 article 存入数据库
        this.baseMapper.insert(articleEntity);

        // 修改 body 的 article_id
        articleBodyService.update(null,
                new LambdaUpdateWrapper<ArticleBodyEntity>()
                        .eq(ArticleBodyEntity::getId, articleBodyEntity.getId())
                        .set(ArticleBodyEntity::getArticleId, articleEntity.getId()));

        // article 与 tags 的关系保存到数据库
        this.addArticleTag(articleEntity.getId(), params.getTags());

    }

    @Override
    public void updateArticleById(ArticleParam params) {
        if (params.getId() == null || params.getId() <= 0 || params.getBody().getId() == null || params.getBody().getId() <= 0) {
            throw new ArticleException("文章id缺失");
        }
        ArticleEntity articleEntity = new ArticleEntity();
        BeanUtils.copyProperties(params, articleEntity);
        articleEntity.setCreateDate(null);
        articleEntity.setUpdateDate(System.currentTimeMillis());
        articleEntity.setBodyId(params.getBody().getId());

        if (params.getCategory().getId() != null && params.getCategory().getId() > 0) {
            articleEntity.setCategoryId(params.getCategory().getId());
        } else {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setCategoryName(params.getCategory().getCategoryName());
            categoryEntity.setCategoryDesc(params.getCategory().getCategoryDesc());
            categoryEntity.setAvatar(params.getCategory().getAvatar());

            categoryService.save(categoryEntity);
            articleEntity.setCategoryId(categoryEntity.getId());
        }

        this.baseMapper.updateById(articleEntity);

        // 更新 body
        ArticleBodyEntity articleBodyEntity = new ArticleBodyEntity();
        BeanUtils.copyProperties(params.getBody(), articleBodyEntity);
        articleBodyEntity.setArticleId(null);
        articleBodyService.updateById(articleBodyEntity);

        // 将 article 和 tag 的关联全部删除
        articleTagService.delTagByArticleId(articleEntity.getId());

        // article 与 tags 的关系保存到数据库
        this.addArticleTag(articleEntity.getId(), params.getTags());
    }

    @Override
    public void setArticleStateById(Long articleId, boolean flag) {

        this.baseMapper.update(null,
                new LambdaUpdateWrapper<ArticleEntity>()
                        .eq(ArticleEntity::getId, articleId)
                        .set(ArticleEntity::getState, flag ? ArticleConstant.Status.ACTIVE.getCode() : ArticleConstant.Status.DOWN.getCode()));
    }

    @Override
    public PageUtils getArticleArchives() {

        return null;
    }

    // 将数据库实体类转换成前端交互数据
    private ArticleVo toVo(ArticleEntity articleEntity, boolean needBody) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(articleEntity, articleVo);

        // 是否查询文章内容
        if (needBody) {
            articleVo.setBody(articleBodyService.getById(articleEntity.getBodyId()));
        }
        articleVo.setCategory(categoryService.getById(articleEntity.getCategoryId()));
        articleVo.setAuthor(userService.toVo(userService.getById(articleEntity.getAuthorId())));

        // 查询标签列表
        List<TagEntity> tags = articleTagService.getTagsByArticleId(articleEntity.getId());

        articleVo.setTags(tags);

        return articleVo;
    }

    // 标签列表和文章建立联系
    private void addArticleTag(Long articleId, List<TagEntity> tags) {
        // 判断 tags 中是否有新增
        tags.forEach(item -> {
            TagEntity tagEntity = new TagEntity();
            ArticleTagEntity articleTagEntity = new ArticleTagEntity();

            articleTagEntity.setArticleId(articleId);

            if (item.getId() != null && item.getId() > 0) {
                articleTagEntity.setTagId(item.getId());
            } else {
                tagEntity.setTagName(item.getTagName());
                tagService.save(tagEntity);

                // 将新增 tag 存入数据库
                articleTagEntity.setTagId(tagEntity.getId());
            }
            // 将 tag 和 article 关系存入数据库
            articleTagService.save(articleTagEntity);
        });
    }

}