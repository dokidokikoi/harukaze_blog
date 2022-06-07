package com.harukaze.blog.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.harukaze.blog.app.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.harukaze.blog.app.entity.ArticleEntity;
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
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = articleService.listArticlePage(params);

        return R.ok().put("data", page);
    }


    /**
     * 查询文章详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ArticleVo article = articleService.getArticleDetailById(id);

        return R.ok().put("article", article);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ArticleEntity article){
		articleService.save(article);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ArticleEntity article){
		articleService.updateById(article);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		articleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
