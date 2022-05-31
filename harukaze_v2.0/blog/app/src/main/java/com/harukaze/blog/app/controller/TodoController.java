package com.harukaze.blog.app.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harukaze.blog.app.entity.TodoEntity;
import com.harukaze.blog.app.service.TodoService;
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
@RequestMapping("app/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = todoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		TodoEntity todo = todoService.getById(id);

        return R.ok().put("todo", todo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TodoEntity todo){
		todoService.save(todo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TodoEntity todo){
		todoService.updateById(todo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		todoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
