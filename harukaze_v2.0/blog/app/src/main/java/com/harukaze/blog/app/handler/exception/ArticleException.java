package com.harukaze.blog.app.handler.exception;

/**
 * @PackageName: com.harukaze.blog.app.handler.exception
 * @ClassName: ArticleException
 * @Description:
 * @Author: doki
 * @Date: 2022/6/8 10:00
 */

public class ArticleException extends RuntimeException {
    public ArticleException() {
    }

    public ArticleException(String message) {
        super(message);
    }

    public ArticleException(String message, Throwable cause) {
        super(message, cause);
    }
}
