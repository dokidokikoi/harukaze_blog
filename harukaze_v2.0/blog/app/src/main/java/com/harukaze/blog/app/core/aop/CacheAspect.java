package com.harukaze.blog.app.core.aop;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.harukaze.blog.app.core.annotation.CacheAnnotation;
import com.harukaze.blog.app.core.annotation.HasPermission;
import com.harukaze.blog.app.util.HttpContextUtils;
import com.harukaze.blog.app.util.UserThreadLocal;
import com.harukaze.blog.common.utils.R;
import com.harukaze.blog.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @PackageName: com.harukaze.blog.app.core.aop
 * @ClassName: CacheAspect
 * @Description:
 * @Author: doki
 * @Date: 2022/6/16 22:33
 */
@Slf4j
@Aspect
@Component
public class CacheAspect {

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("@annotation(com.harukaze.blog.app.core.annotation.CacheAnnotation)")
    public void pc(){}

    // redis 缓存
    @Around("pc()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        CacheAnnotation annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(CacheAnnotation.class);
        String redisKey = "cache_" + annotation.cacheName();
        long expireSecond = annotation.expireSecond();

        // 如果缓存中有数据，返回缓存数据
        String json = (String) redisUtil.get(redisKey);
        if (!StrUtil.isBlank(json)) {
            log.info("从缓存中获取，{}", redisKey);
            return JSONUtil.toBean(json, R.class);
        }

        // 没有则查询数据库，并将结果存入缓存
        log.info("查询数据库，{}", redisKey);
        Object result = joinPoint.proceed();
        redisUtil.set(redisKey, JSONUtil.toJsonStr(result), expireSecond);

        return result;
    }
}
