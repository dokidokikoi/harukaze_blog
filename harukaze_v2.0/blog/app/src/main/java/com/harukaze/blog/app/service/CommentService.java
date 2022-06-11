package com.harukaze.blog.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.blog.app.param.CommentParam;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.app.entity.CommentEntity;

import java.util.Map;

/**
 * 
 *
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:17
 */
public interface CommentService extends IService<CommentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils listCommentPage(Long articleId,  Map<String, Object> params);

    void saveComment(CommentParam param);

    void updateComment(CommentEntity comment) throws Exception;

    Object countComment(Long id);
}

