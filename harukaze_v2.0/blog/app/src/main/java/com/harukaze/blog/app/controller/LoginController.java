package com.harukaze.blog.app.controller;

import com.harukaze.blog.app.param.LoginParam;
import com.harukaze.blog.app.service.LoginService;
import com.harukaze.blog.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @PackageName: com.harukaze.blog.app.controller
 * @ClassName: LoginController
 * @Description:
 * @Author: doki
 * @Date: 2022/6/7 11:09
 */

@RestController
@RequestMapping("app")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("captcha")
    public R getCaptcha() {
        return loginService.getCaptcha();
    }

    @PostMapping("login")
    public R login(@Validated LoginParam loginParam) {
        return loginService.login(loginParam);
    }

    @PostMapping("logout")
    public R logout(HttpServletRequest request) {
        System.out.println("hello");
        return loginService.logout(request);
    }
}
