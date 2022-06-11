package com.harukaze.blog.app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.harukaze.blog.app.core.annotation.AccessLimit;
import com.harukaze.blog.app.core.annotation.HasPermission;
import com.harukaze.blog.app.core.annotation.LogAnnotation;
import com.harukaze.blog.app.service.ArticleTagService;
import com.harukaze.blog.common.constant.ResponseStatus;
import com.harukaze.blog.common.valid.AddGroup;
import com.harukaze.blog.common.valid.UpdateGroup;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.harukaze.blog.app.entity.TagEntity;
import com.harukaze.blog.app.service.TagService;
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
@RequestMapping("app/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleTagService articleTagService;

    /**
     * 查询标签列表
     * {
     * 	"key": "te",
     * 	"page": 1,
     * 	"limit": 10
     * }
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tagService.listTagPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		TagEntity tag = tagService.getById(id);

        return R.ok().put("data", tag);
    }

    /**
     * 新增标签
     *
     */
    @LogAnnotation(module = "标签", operator = "新增标签")
    @HasPermission("article:add")
    @AccessLimit
    @PostMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody TagEntity tag){
		tagService.save(tag);

        return R.ok();
    }

    /**
     * 修改
     */
    @LogAnnotation(module = "标签", operator = "修改标签")
    @HasPermission("article:update")
    @AccessLimit
    @PutMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody TagEntity tag){
		tagService.updateById(tag);

        return R.ok();
    }

    /**
     * 删除
     * 标签下没有文章，可删除
     */
    @LogAnnotation(module = "标签", operator = "删除标签")
    @HasPermission("article:update")
    @AccessLimit
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id){
		boolean flag = tagService.removeTagById(id);

        return flag ? R.ok() : R.error(ResponseStatus.TAG_DELETE_ERR.getCode(), ResponseStatus.TAG_DELETE_ERR.getMsg());
    }

    /**
     * 查询标签下的文章、评论、观看数
     */
    @GetMapping("/articles")
    public R getTagData(){
        List<Map<String, Object>> data = articleTagService.getData();

        return R.ok().put("data", data);
    }

    @GetMapping("/count")
    public R getTotalCount() {
        return R.ok().put("data", tagService.count());
    }

}
