package com.harukaze.blog.app.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.blog.app.core.annotation.LogAnnotation;
import com.harukaze.blog.app.dao.ArticleDao;
import com.harukaze.blog.app.dao.CommonDao;
import com.harukaze.blog.app.entity.ArticleEntity;
import com.harukaze.blog.app.entity.LogEntity;
import com.harukaze.blog.app.handler.exception.ArticleUpdateCommentException;
import com.harukaze.blog.app.handler.exception.ArticleUpdateViewException;
import com.harukaze.blog.app.service.LogService;
import com.harukaze.blog.app.service.ThreadService;
import com.harukaze.blog.app.service.UserService;
import com.harukaze.blog.app.util.HttpContextUtils;
import com.harukaze.blog.app.util.IpUtils;
import com.harukaze.blog.app.util.UserAgentUtils;
import com.harukaze.blog.app.util.UserThreadLocal;
import com.harukaze.blog.app.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @PackageName: com.harukaze.blog.app.service.impl
 * @ClassName: ThreadServiceImpl
 * @Description:
 * @Author: doki
 * @Date: 2022/6/9 10:02
 */
@Slf4j
@Service
public class ThreadServiceImpl implements ThreadService {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private LogService logService;

    @Autowired
    private UserAgentUtils userAgentUtils;

    @Autowired
    private CommonDao commonDao;

    private final int MAX_TRY = 10;

    @Override
    @Async("taskExecutor")
    public void updateUserLastLogin(Long id) {

        // 由于更新用户最后登录时间对线程安全要求不高，不考虑线程安全问题
        userService.updateLastLogin(id);
    }

    @Override
    @Async("taskExecutor")
    public void updateArticleViewCountById(Long articleId) {
        int count = 0;

        // 使用 cas 保证线程安全，自旋 MAX_TRY 次还未成功则抛出异常
        while (count++ < MAX_TRY) {
            int view = articleDao.selectViewCountById(articleId);
            ArticleEntity articleEntity = new ArticleEntity();
            articleEntity.setViewCounts(view+1);

            int update = articleDao.update(articleEntity,
                    new LambdaQueryWrapper<ArticleEntity>()
                            .eq(ArticleEntity::getId, articleId)
                            .eq(ArticleEntity::getViewCounts, view));
            if (update == 1) {
                break;
            }
        }
        if (count == 11) {
            throw new ArticleUpdateViewException("更新文章浏览数错误,文章id："+articleId);
        }
    }

    @Override
    @Async("taskExecutor")
    public void updateArticleCommentCountsById(Long articleId, Integer cnt) {
        int count = 0;

        // 使用 cas 保证线程安全，自旋 MAX_TRY 次还未成功则抛出异常
        while (count++ < MAX_TRY) {
            int comment = articleDao.selectCommentCountById(articleId);
            ArticleEntity articleEntity = new ArticleEntity();
            // 评论数加一或者减一
            articleEntity.setCommentCounts(comment+cnt);

            int update = articleDao.update(articleEntity,
                    new LambdaQueryWrapper<ArticleEntity>()
                            .eq(ArticleEntity::getId, articleId)
                            .eq(ArticleEntity::getCommentCounts, comment));
            if (update == 1) {
                break;
            }
        }
        if (count == 11) {
            throw new ArticleUpdateCommentException("更新文章评论错误,文章id："+articleId);
        }
    }

    @Override
    @Async("taskExecutor")
    public void recordLog(ProceedingJoinPoint joinPoint, Long time, HttpServletRequest request, UserVo userVo) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        LogEntity logEntity = new LogEntity();
        if (userVo != null) {
            logEntity.setUserId(userVo.getId());
        }

        logEntity.setCreateDate(System.currentTimeMillis());
        logEntity.setMethod(signature.getName());
        logEntity.setModule(logAnnotation.module());
        logEntity.setOperation(logAnnotation.operator());
        //请求参数
//        Object[] args = joinPoint.getArgs();
//        if (args.length > 0 && args[0] != null) {
//            Field[] fields=args[0].getClass().getDeclaredFields();
//            String[] fieldNames=new String[fields.length];
//            for(int i=0;i<fields.length;i++){
//                System.out.println(fields[i].getType());
//                fieldNames[i]=fields[i].getName();
//            }
//            String params = JSONUtil.toJsonPrettyStr(fieldNames);
//            logEntity.setParams(params);
//        }

        //获取request, 设置ip地址
        Long ipAddrNum = IpUtils.getIpAddrNum(request);
        Long exist = commonDao.selectIp(ipAddrNum);
        if (exist != null) {
            logEntity.setIpId(exist);
        } else {
            commonDao.insertIp(ipAddrNum);
            logEntity.setIpId(commonDao.selectIp(ipAddrNum));
        }

        logEntity.setTime(time);
        logEntity.setAddress(IpUtils.getCityInfo(IpUtils.getIpAddr(request)));
        Map<String, String> map = userAgentUtils.parseOsAndBrowser(request.getHeader("User-Agent"));

        // 获取os
        String os = map.get(UserAgentUtils.OS);
        exist = commonDao.selectOs(os);
        if (exist != null) {
            logEntity.setOsId(exist);
        } else {
            commonDao.insertOs(os);
            logEntity.setOsId(commonDao.selectIp(ipAddrNum));
        }

        // 获取浏览器
        String browser = map.get(UserAgentUtils.BROWSER);
        exist = commonDao.selectBrowser(browser);
        if (exist != null) {
            logEntity.setBrowserId(exist);
        } else {
            commonDao.insertBrowser(browser);
            logEntity.setBrowserId(commonDao.selectBrowser(browser));
        }

        logService.save(logEntity);

        log.info("=======================log============================");
        log.info("module:{}",logEntity.getModule());
        log.info("operation:{}",logEntity.getOperation());

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        log.info("request method:{}",className+"."+logEntity.getMethod()+"()");
        log.info("params:{}",logEntity.getParams());
        log.info("ip:{}", IpUtils.ipToString(ipAddrNum));
        log.info("excute time : {} ms", logEntity.getTime());
        log.info("browser:{}", browser);
        log.info("=======================log end============================");
    }
}
