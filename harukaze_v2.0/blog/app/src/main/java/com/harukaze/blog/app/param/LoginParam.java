package com.harukaze.blog.app.param;

import lombok.Data;

/**
 * @PackageName: com.harukaze.blog.app.param
 * @ClassName: LoginParam
 * @Description:
 * @Author: doki
 * @Date: 2022/6/7 11:24
 */
@Data
public class LoginParam {
    private String account;
    private String password;
    private String code;
    private String key;
}
