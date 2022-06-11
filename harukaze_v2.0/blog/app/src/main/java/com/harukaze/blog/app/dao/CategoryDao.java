package com.harukaze.blog.app.dao;

import com.harukaze.blog.app.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 * 
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:36
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {

    @Select("select id from hk_category")
    List<Long> selectIds();
}
