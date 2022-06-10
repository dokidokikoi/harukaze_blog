package com.harukaze.blog.app.handler;

import com.harukaze.blog.app.handler.exception.ArticleException;
import com.harukaze.blog.app.handler.exception.NotLoginException;
import com.harukaze.blog.app.handler.exception.NotPermissionException;
import com.harukaze.blog.common.constant.ResponseStatus;
import com.harukaze.blog.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @PackageName: com.harukaze.costume.app.handler
 * @ClassName: ExceptionHandler
 * @Description:
 * @Author: doki
 * @Date: 2022/6/2 9:34
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Exception e) {
        log.error("{}",e);
        return R.error(HttpStatus.SC_BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(NotLoginException.class)
    public R notLoginHandler(NotLoginException e, HttpServletResponse resp) {
        return R.error(ResponseStatus.NOT_LOGIN.getCode(), ResponseStatus.NOT_LOGIN.getMsg());
    }

    @ExceptionHandler(ArticleException.class)
    public R articleExceptionHandler(NotLoginException e, HttpServletResponse resp) {
        return R.error(ResponseStatus.ARTICLE_BAD_REQEST.getCode(), e.getMessage());
    }

    @ExceptionHandler(NotPermissionException.class)
    public R notPermissionExceptionHandler(NotPermissionException e, HttpServletResponse resp) {
        return R.error(ResponseStatus.USER_FORBIDDEN.getCode(), ResponseStatus.USER_FORBIDDEN.getMsg());
    }
}
