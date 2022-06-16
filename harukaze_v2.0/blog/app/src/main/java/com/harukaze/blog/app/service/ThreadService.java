package com.harukaze.blog.app.service;

import com.harukaze.blog.app.entity.ArticleEntity;
import com.harukaze.blog.app.vo.UserVo;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface ThreadService {
    void updateUserLastLogin(Long id);

    void updateArticleViewCountById(Long id);

    void updateArticleCommentCountsById(Long articleId, Integer cnt);

    void recordLog(ProceedingJoinPoint joinPoint, Long time, HttpServletRequest request, UserVo userVo) throws IOException;
}
