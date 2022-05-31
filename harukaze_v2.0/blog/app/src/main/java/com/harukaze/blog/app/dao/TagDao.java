package com.harukaze.blog.app.dao;

import com.harukaze.blog.app.entity.TagEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:17
 */
@Mapper
public interface TagDao extends BaseMapper<TagEntity> {
	
}
