package com.harukaze.blog.app.dao;

import com.harukaze.blog.app.entity.ArticleEntity;
import com.harukaze.blog.app.entity.ArticleTagEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harukaze.blog.app.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:17
 */
@Mapper
public interface ArticleTagDao extends BaseMapper<ArticleTagEntity> {

    List<TagEntity> selectTagListByArticleId(@Param("id") Long id);

    List<ArticleEntity> selectArticleListByTagId(Long id);
}
