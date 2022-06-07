package com.harukaze.blog.common.constant;

import cn.hutool.http.HttpStatus;

/**
 * @PackageName: com.harukaze.costume.common.constant
 * @ClassName: ResponseStatus
 * @Description:
 * @Author: doki
 * @Date: 2022/6/3 15:50
 */

public enum  ResponseStatus {
    LOGIN_EXPIRE(41000, "登录已过期"),
    NOT_LOGIN(40000, "未登录"),
    USER_NOT_FIND(42000, "用户不存在"),
    USER_FORBIDDEN(42001, "权限不足，请联系管理员");

    private Integer code;
    private String msg;

    ResponseStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
