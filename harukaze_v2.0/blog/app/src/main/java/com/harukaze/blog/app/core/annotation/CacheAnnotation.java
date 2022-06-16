package com.harukaze.blog.app.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheAnnotation {
    String cacheName();
    long expireSecond() default 60 * 60 * 24 * 7;
}
