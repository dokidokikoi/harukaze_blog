package com.harukaze.blog.app.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @PackageName: com.harukaze.blog.app.param
 * @ClassName: LoginParam
 * @Description:
 * @Author: doki
 * @Date: 2022/6/7 11:24
 */
@Data
public class LoginParam {
    @NotBlank(message = "account不能为空")
    private String account;
    @NotBlank(message = "password不能为空")
    private String password;
    @NotBlank(message = "code不能为空")
    private String code;
    @NotBlank(message = "key不能为空")
    private String key;
}
