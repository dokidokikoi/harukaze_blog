package com.harukaze.blog.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.harukaze.blog.app.core.annotation.AccessLimit;
import com.harukaze.blog.app.core.annotation.HasPermission;
import com.harukaze.blog.app.core.annotation.LogAnnotation;
import com.harukaze.blog.app.param.TodoParam;
import com.harukaze.blog.common.valid.AddGroup;
import com.harukaze.blog.common.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
     * {
     * 	"key": "",
     * 	"days": 7,
     * 	"page": 1,
     * 	"limit": 5
     * }
     */
    @AccessLimit
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = todoService.listTodoPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @AccessLimit
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		TodoEntity todo = todoService.getById(id);

        return R.ok().put("data", todo);
    }

    /**
     * 保存
     */
    @LogAnnotation(module = "代办事项", operator = "新增事项")
//    @HasPermission("todo:add")
    @AccessLimit
    @PostMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody TodoEntity todo){
		todoService.saveTodo(todo);

        return R.ok();
    }

    /**
     * 修改
     */
    @LogAnnotation(module = "代办事项", operator = "修改事项")
//    @HasPermission("todo:update")
    @AccessLimit
    @PutMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody TodoParam todo){
		todoService.updateTodo(todo);

        return R.ok();
    }

    /**
     * 删除
     */
    @LogAnnotation(module = "代办事项", operator = "删除事项")
//    @HasPermission("todo:delete")
    @AccessLimit
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id){
		todoService.removeById(id);

        return R.ok();
    }

    @GetMapping("/count")
    public R getTotalCount() {
        return R.ok().put("data", todoService.count());
    }

}
