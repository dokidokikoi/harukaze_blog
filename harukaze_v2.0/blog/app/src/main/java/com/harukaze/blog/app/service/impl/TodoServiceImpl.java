package com.harukaze.blog.app.service.impl;

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

}