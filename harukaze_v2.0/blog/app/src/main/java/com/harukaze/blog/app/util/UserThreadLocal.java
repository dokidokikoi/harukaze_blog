package com.harukaze.blog.app.util;

import com.harukaze.blog.app.vo.UserVo;

/**
 * @PackageName: com.harukaze.blog.app.util
 * @ClassName: UserThreadLocal
 * @Description:
 * @Author: doki
 * @Date: 2022/6/7 15:21
 */

public class UserThreadLocal {
    // 存储用户数据，一次请求结束后删除
    private static final ThreadLocal<UserVo> LOCAL = new ThreadLocal<>();

    private UserThreadLocal() {
    }

    public static UserVo get() {
        return LOCAL.get();
    }

    public static void set(UserVo userVo) {
        LOCAL.set(userVo);
    }

    public static void remove() {
        LOCAL.remove();
    }
}
