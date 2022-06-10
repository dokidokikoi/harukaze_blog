package com.harukaze.blog.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.blog.app.param.TodoParam;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.app.entity.TodoEntity;

import java.util.Map;

/**
 * 
 *
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:36
 */
public interface TodoService extends IService<TodoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils listTodoPage(Map<String, Object> params);

    void saveTodo(TodoEntity todo);

    void updateTodo(TodoParam todo);
}

