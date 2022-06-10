package com.harukaze.blog.app.handler.exception;

/**
 * @PackageName: com.harukaze.blog.app.handler.exception
 * @ClassName: BlanckAuthorException
 * @Description:
 * @Author: doki
 * @Date: 2022/6/8 17:03
 */

public class BlanckAuthorException extends RuntimeException {
    public BlanckAuthorException() {
    }

    public BlanckAuthorException(String message) {
        super(message);
    }

    public BlanckAuthorException(String message, Throwable cause) {
        super(message, cause);
    }
}
