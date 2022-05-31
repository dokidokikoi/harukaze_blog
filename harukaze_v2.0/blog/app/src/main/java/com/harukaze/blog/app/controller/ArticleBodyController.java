package com.harukaze.blog.app.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harukaze.blog.app.entity.ArticleBodyEntity;
import com.harukaze.blog.app.service.ArticleBodyService;
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
@RequestMapping("app/articlebody")
public class ArticleBodyController {
    @Autowired
    private ArticleBodyService articleBodyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = articleBodyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		ArticleBodyEntity articleBody = articleBodyService.getById(id);

        return R.ok().put("articleBody", articleBody);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ArticleBodyEntity articleBody){
		articleBodyService.save(articleBody);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ArticleBodyEntity articleBody){
		articleBodyService.updateById(articleBody);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		articleBodyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
