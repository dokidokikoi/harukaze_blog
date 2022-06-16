package com.harukaze.blog.app.handler.exception;

/**
 * @PackageName: com.harukaze.blog.app.handler.exception
 * @ClassName: ArticleNotFoundException
 * @Description:
 * @Author: doki
 * @Date: 2022/6/16 17:25
 */

public class ArticleNotFoundException extends Exception {
    public ArticleNotFoundException() {
        super();
    }

    public ArticleNotFoundException(String message) {
        super(message);
    }

    public ArticleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
