package com.harukaze.blog.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harukaze.blog.app.entity.ArticleEntity;
import com.harukaze.blog.app.param.TodoParam;
import com.harukaze.blog.common.constant.ToDoConstant;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.Query;

import com.harukaze.blog.app.dao.TodoDao;
import com.harukaze.blog.app.entity.TodoEntity;
import com.harukaze.blog.app.service.TodoService;


@Service("todoService")
public class TodoServiceImpl extends ServiceImpl<TodoDao, TodoEntity> implements TodoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TodoEntity> page = this.page(
                new Query<TodoEntity>().getPage(params),
                new QueryWrapper<TodoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils listTodoPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        Integer days = (Integer) params.get("days");
        Boolean done = (Boolean) params.get("done");

        // 分页字段
        int currt = (Integer) params.getOrDefault("page", "1");
        int limit = (Integer) params.getOrDefault("limit", "1000");

        Page<ArticleEntity> page = new Page<>(currt, limit);

        IPage<TodoEntity> iPage = this.baseMapper.selectTodoList(page,
                key,
                days,
                done == null ? null : done ? ToDoConstant.Status.DONE.getCode() : ToDoConstant.Status.NEW.getCode());

        return new PageUtils(iPage);
    }

    @Override
    public void saveTodo(TodoEntity todo) {
        todo.setIsDone(ToDoConstant.Status.NEW.getCode());
        todo.setCreateDate(System.currentTimeMillis());

        this.baseMapper.insert(todo);
    }

    @Override
    public void updateTodo(TodoParam todo) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setId(todo.getId());
        todoEntity.setContent(todo.getContent());
        todoEntity.setIsDone(todo.getDone() ? ToDoConstant.Status.DONE.getCode() : ToDoConstant.Status.NEW.getCode());

        this.baseMapper.updateById(todoEntity);
    }

}