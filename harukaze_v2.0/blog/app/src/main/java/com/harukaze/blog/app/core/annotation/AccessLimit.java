package com.harukaze.blog.app.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    boolean isMustLogin() default true;

    int seconds() default 1;

    int maxCount() default 10;

    String msg() default "操作频率过高";
}
