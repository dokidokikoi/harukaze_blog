package com.harukaze.blog.app.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harukaze.blog.app.entity.ArticleEntity;
import com.harukaze.blog.app.entity.TodoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:36
 */
@Mapper
public interface TodoDao extends BaseMapper<TodoEntity> {

    IPage<TodoEntity> selectTodoList(Page<ArticleEntity> page,
                                     @Param("key") String key,
                                     @Param("days") Integer days,
                                     @Param("done") Integer done);
}
