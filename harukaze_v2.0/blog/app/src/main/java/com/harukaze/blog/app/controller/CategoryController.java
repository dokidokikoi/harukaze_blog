package com.harukaze.blog.app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.harukaze.blog.app.core.annotation.AccessLimit;
import com.harukaze.blog.app.core.annotation.HasPermission;
import com.harukaze.blog.app.core.annotation.LogAnnotation;
import com.harukaze.blog.common.constant.ResponseStatus;
import com.harukaze.blog.common.valid.AddGroup;
import com.harukaze.blog.common.valid.UpdateGroup;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.harukaze.blog.app.entity.CategoryEntity;
import com.harukaze.blog.app.service.CategoryService;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.R;



/**
 * 
 *
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:36
 */
@RestController
@RequestMapping("app/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询文章分类列表
     * {
     * 	"key": "te",
     * 	"page": 1,
     * 	"limit": 10
     * }
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryService.listCategoryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		CategoryEntity category = categoryService.getById(id);

        return R.ok().put("data", category);
    }

    /**
     * 新增分类
     */
    @LogAnnotation(module = "文章分类", operator = "新增分类")
    @HasPermission("article:add")
    @AccessLimit
    @PostMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @LogAnnotation(module = "文章分类", operator = "修改分类")
    @HasPermission("article:update")
    @AccessLimit
    @PutMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody CategoryEntity category){
		categoryService.updateById(category);

        return R.ok();
    }

    /**
     * 删除
     * 分类下没有文章可删除
     */
    @LogAnnotation(module = "文章分类", operator = "删除分类")
    @HasPermission("article:update")
    @AccessLimit
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id){
        boolean b = categoryService.removeCategoryById(id);

        return b ? R.ok() : R.error(ResponseStatus.CATEGORY_DELETE_ERR.getCode(), ResponseStatus.CATEGORY_DELETE_ERR.getMsg());
    }

    /**
     * 查询分类下的文章、评论、观看数
     */
    @GetMapping("/articles")
    public R getCategoryData(){
        List<Map<String, Object>> data = categoryService.getCategoryData();

        return R.ok().put("data", data);
    }

    @GetMapping("/count")
    public R getTotalCount() {
        return R.ok().put("data", categoryService.count());
    }

}
