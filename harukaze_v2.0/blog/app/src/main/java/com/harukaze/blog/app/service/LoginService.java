package com.harukaze.blog.app.service;

import com.harukaze.blog.app.param.LoginParam;
import com.harukaze.blog.common.utils.R;

import javax.servlet.http.HttpServletRequest;

/**
 * @PackageName: com.harukaze.blog.app.service
 * @ClassName: LoginService
 * @Description:
 * @Author: doki
 * @Date: 2022/6/7 11:12
 */

public interface LoginService {
    R getCaptcha();

    R login(LoginParam loginParam);

    R logout(HttpServletRequest request);
}
