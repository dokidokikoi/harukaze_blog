package com.harukaze.blog.app.interceptor;

import cn.hutool.json.JSONUtil;
import com.harukaze.blog.app.core.annotation.AccessLimit;
import com.harukaze.blog.app.util.IpUtils;
import com.harukaze.blog.common.constant.ResponseStatus;
import com.harukaze.blog.common.utils.R;
import com.harukaze.blog.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @PackageName: com.harukaze.blog.app.interceptor
 * @ClassName: AccessLimitInterceptor
 * @Description:
 * @Author: doki
 * @Date: 2022/6/16 9:58
 */
@Component
@Slf4j
public class AccessLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        return isBand(request, response);
    }

    // ip 访问某方法频率过高则屏蔽
    private boolean isBand(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {

        int seconds = 1;
        int maxCount = 10;
        String ip = IpUtils.getIpAddr(request);
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        String redisKey = ip + ":" + method + ":" + requestURI;
        String countStr = (String) redisUtil.get(redisKey);


        if (countStr == null) {
            //在规定周期内第一次访问，存入redis
            redisUtil.set(redisKey, "1", seconds);
        } else {
            int count = Integer.parseInt(countStr);
            if (count >= maxCount) {
                //超出访问限制次数
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                R r = R.error(ResponseStatus.USER_FORBIDDEN.getCode(), "访问频率过高");
                out.write(JSONUtil.toJsonStr(r));
                out.flush();
                out.close();
                return false;
            } else {
                //没超出访问限制次数
                redisUtil.incr(redisKey, 1);
            }
        }
        return true;
    }
}
