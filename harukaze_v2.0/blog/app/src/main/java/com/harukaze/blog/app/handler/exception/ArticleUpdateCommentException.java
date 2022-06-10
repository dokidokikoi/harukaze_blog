package com.harukaze.blog.app.handler.exception;

/**
 * @PackageName: com.harukaze.blog.app.handler.exception
 * @ClassName: ArticleCommentException
 * @Description:
 * @Author: doki
 * @Date: 2022/6/9 10:48
 */

public class ArticleUpdateCommentException extends RuntimeException {
    public ArticleUpdateCommentException() {
    }

    public ArticleUpdateCommentException(String message) {
        super(message);
    }

    public ArticleUpdateCommentException(String message, Throwable cause) {
        super(message, cause);
    }
}
