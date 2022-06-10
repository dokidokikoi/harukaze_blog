package com.harukaze.blog.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.harukaze.blog.app.core.annotation.AccessLimit;
import com.harukaze.blog.app.core.annotation.HasPermission;
import com.harukaze.blog.app.core.annotation.LogAnnotation;
import com.harukaze.blog.app.util.IpUtils;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.harukaze.blog.app.entity.AnimeEntity;
import com.harukaze.blog.app.service.AnimeService;
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
@RequestMapping("app/anime")
public class AnimeController {
    @Autowired
    private AnimeService animeService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestBody Map<String, Object> params){
        PageUtils page = animeService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		AnimeEntity anime = animeService.getById(id);

        return R.ok().put("anime", anime);
    }

    /**
     * 保存
     */
    @LogAnnotation(module = "动画", operator = "新增动画")
    @HasPermission("anime:add")
    @AccessLimit
    @PostMapping("/save")
    public R save(@RequestBody AnimeEntity anime){
		animeService.save(anime);

        return R.ok();
    }

    /**
     * 修改
     */
    @LogAnnotation(module = "动画", operator = "修改动画")
    @HasPermission("anime:update")
    @AccessLimit
    @PutMapping("/update")
    public R update(@RequestBody AnimeEntity anime){
		animeService.updateById(anime);

        return R.ok();
    }

    /**
     * 删除
     */
    @LogAnnotation(module = "动画", operator = "删除动画")
    @HasPermission("anime:delete")
    @AccessLimit
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id){
		animeService.removeById(id);

        return R.ok();
    }

}
