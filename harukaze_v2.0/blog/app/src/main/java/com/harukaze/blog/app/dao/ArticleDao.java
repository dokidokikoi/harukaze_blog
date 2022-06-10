package com.harukaze.blog.app.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harukaze.blog.app.entity.ArticleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * 
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:17
 */
@Repository
@Mapper
public interface ArticleDao extends BaseMapper<ArticleEntity> {

    IPage<ArticleEntity> selectArticleList(Page<ArticleEntity> page,
                                           @Param("key") String key,
                                           @Param("categoryId") Long categoryId,
                                           @Param("tags") List<Long> tags,
                                           @Param("time") String time,
                                           @Param("view") String view,
                                           @Param("comment") String comment);

    @Select("SELECT view_counts FROM hk_article WHERE id = #{id}")
    int selectViewCountById(@Param("id") Long id);

    @Select("SELECT comment_counts FROM hk_article WHERE id = #{id}")
    int selectCommentCountById(Long articleId);
}
