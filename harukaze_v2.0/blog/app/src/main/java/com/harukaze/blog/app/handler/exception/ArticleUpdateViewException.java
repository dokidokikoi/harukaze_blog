package com.harukaze.blog.app.handler.exception;

/**
 * @PackageName: com.harukaze.blog.app.handler.exception
 * @ClassName: ArticleUpdateViewException
 * @Description:
 * @Author: doki
 * @Date: 2022/6/9 10:38
 */

public class ArticleUpdateViewException extends RuntimeException {
    public ArticleUpdateViewException() {
    }

    public ArticleUpdateViewException(String message) {
        super(message);
    }

    public ArticleUpdateViewException(String message, Throwable cause) {
        super(message, cause);
    }
}
