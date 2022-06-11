package com.harukaze.blog.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.harukaze.blog.app.core.annotation.AccessLimit;
import com.harukaze.blog.app.core.annotation.HasPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.harukaze.blog.app.entity.LogEntity;
import com.harukaze.blog.app.service.LogService;
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
@RequestMapping("app/log")
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 列表
     * {
     * 	"key": "",
     * 	"page": 1,
     * 	"limit": 5
     * }
     */
    @AccessLimit
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logService.listLogPage(params);

        return R.ok().put("data", page);
    }


//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{id}")
//    public R info(@PathVariable("id") Long id){
//		LogEntity log = logService.getById(id);
//
//        return R.ok().put("log", log);
//    }
//
//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    public R save(@RequestBody LogEntity log){
//		logService.save(log);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    public R update(@RequestBody LogEntity log){
//		logService.updateById(log);
//
//        return R.ok();
//    }

    /**
     * 删除
     */
    @HasPermission("log:delete")
    @AccessLimit
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id){
		logService.removeById(id);

        return R.ok();
    }

}
