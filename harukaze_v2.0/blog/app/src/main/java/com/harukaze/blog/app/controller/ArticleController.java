package com.harukaze.blog.app.controller;

import java.util.Map;

import com.harukaze.blog.app.core.annotation.AccessLimit;
import com.harukaze.blog.app.core.annotation.HasPermission;
import com.harukaze.blog.app.core.annotation.LogAnnotation;
import com.harukaze.blog.app.param.ArticleParam;
import com.harukaze.blog.app.vo.ArticleVo;
import com.harukaze.blog.common.valid.AddGroup;
import com.harukaze.blog.common.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.harukaze.blog.app.service.ArticleService;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.R;



/**
 * 
 *
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:17
 */
@RestController
@RequestMapping("app/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 分页查询、条件查询
     * {
     * 	   "key": "summer",	// 文章标题关键字，标签名，分类名
     *     "category": "1",	// 分类 id
     *     "tags": [1, 3],		// 标签 id
     *     "limit": 10,		// 分页大小
     *     "page": 1,			// 当前页
     *     "sort": {			// 排序
     *         "time": "desc",
     *         “view”: "asc",
     *         "comment": "desc"
     *     }
     * }
     */
    @LogAnnotation(module = "文章", operator = "查询文章列表")
    @PostMapping("/list")
    public R list(@RequestBody Map<String, Object> params){
        PageUtils page = articleService.listArticlePage(params);

        return R.ok().put("data", page);
    }


    /**
     * 查询文章详情
     */
    @LogAnnotation(module = "文章", operator = "查询文章详情")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ArticleVo article = articleService.getArticleDetailById(id);

        return R.ok().put("data", article);
    }

    /**
     * 写文章
     */
    @LogAnnotation(module = "文章", operator = "写文章")
    @HasPermission("article:add")
    @AccessLimit
    @PostMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody ArticleParam params){
		articleService.saveArticleDetail(params);

        return R.ok();
    }

    /**
     * 修改文章
     */
    @LogAnnotation(module = "文章", operator = "修改文章")
    @HasPermission("article:update")
    @AccessLimit
    @PutMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody ArticleParam params){
		articleService.updateArticleById(params);

        return R.ok();
    }

    /**
     * 设置文章状态
     */
    @LogAnnotation(module = "文章", operator = "设置文章状态")
    @HasPermission("article:update")
    @AccessLimit
    @PutMapping("/set_state")
    public R delete(Long id, boolean flag){
		articleService.setArticleStateById(id, flag);

        return R.ok();
    }

    @GetMapping("/count")
    public R getTotalCount() {
        return R.ok().put("data", articleService.count());
    }
}
