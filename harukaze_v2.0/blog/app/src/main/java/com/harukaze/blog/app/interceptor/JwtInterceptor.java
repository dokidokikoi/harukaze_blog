package com.harukaze.blog.app.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.blog.app.core.annotation.AccessLimit;
import com.harukaze.blog.app.entity.UserEntity;
import com.harukaze.blog.app.service.ThreadService;
import com.harukaze.blog.app.service.UserService;
import com.harukaze.blog.app.util.UserThreadLocal;
import com.harukaze.blog.app.vo.UserVo;
import com.harukaze.blog.common.constant.ResponseStatus;
import com.harukaze.blog.common.constant.UserConstant;
import com.harukaze.blog.app.util.IpUtils;
import com.harukaze.blog.common.utils.JwtUtils;
import com.harukaze.blog.common.utils.R;
import com.harukaze.blog.common.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @PackageName: com.harukaze.costume.app.interceptor
 * @ClassName: JwtInterceptor
 * @Description:
 * @Author: doki
 * @Date: 2022/6/2 9:03
 */

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ThreadService threadService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        AccessLimit accessLimit = null;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
        }

        //方法上有访问控制的注解
//        if (accessLimit != null) {
//            // ip 访问频率是否过高
//            if (!isBand(request, response, accessLimit)) {
//                return false;
//            }
//        }


        // 验证登录
        response.setContentType("application/json;charset=utf-8");
        String token = request.getHeader(JwtUtils.getHeader());
        R r = null;
        if(token != null){
            Claims claim = JwtUtils.getClaimByToken(token);
            try {
                if (claim != null && !JwtUtils.isTokenExpired(claim)) {
                    String username = claim.getSubject();

                    // 用 token 从 redis 取出 username，查询数据库，将用户信息存入 LocalThread
                    String redisUsername = (String) redisUtil.get("token_" + token);
                    if (!StrUtil.isBlank(username) || !StrUtil.isBlank(redisUsername)) {
                        UserEntity user = userService.getOne(
                                new LambdaQueryWrapper<UserEntity>()
                                        .eq(UserEntity::getAccount, username));

                        // 用户不存在，或者用户被冻结，不予通过
                        if (user != null && user.getState() == UserConstant.Status.USER_UP.getCode()) {
                            UserVo userVo = userService.toVo(user);
                            UserThreadLocal.set(userVo);

                            // 更新用户最后登录时间，与用户业务无关，放入线程池
                            threadService.updateUserLastLogin(user.getId());
                            return true;
                        } else {
                            r = R.error(ResponseStatus.USER_NOT_FIND.getCode(), ResponseStatus.USER_NOT_FIND.getMsg());
                        }
                    } else {
                        r = R.error(ResponseStatus.LOGIN_EXPIRE.getCode(), ResponseStatus.LOGIN_EXPIRE.getMsg());
                    }
                } else {
                    r = R.error(ResponseStatus.LOGIN_EXPIRE.getCode(), ResponseStatus.LOGIN_EXPIRE.getMsg());
                }
            } catch (Exception e) {
                log.error("token 解析错误，error:{}", e.getClass());
                r = R.error(ResponseStatus.LOGIN_EXPIRE.getCode(), ResponseStatus.LOGIN_EXPIRE.getMsg());
                response.getWriter().write(JSONUtil.toJsonStr(r));
                return false;
            }
        } else {
            if (accessLimit == null || !accessLimit.isMustLogin()) {
                return true;
            }
            r = R.error(ResponseStatus.USER_NOT_FIND.getCode(), ResponseStatus.USER_NOT_FIND.getMsg());
        }
        response.getWriter().write(JSONUtil.toJsonStr(r));
        return false;
    }

    // 一次请求结束之后，将 TreadLocal 释放，防止内存溢出
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        UserThreadLocal.remove();
        System.out.println(UserThreadLocal.get());
    }

}
