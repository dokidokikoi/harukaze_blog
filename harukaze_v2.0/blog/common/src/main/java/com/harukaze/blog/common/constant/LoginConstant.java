package com.harukaze.blog.common.constant;

/**
 * @PackageName: com.harukaze.blog.common.constant
 * @ClassName: LoginContant
 * @Description:
 * @Author: doki
 * @Date: 2022/6/7 11:20
 */

public class LoginConstant {
    public enum Expire {
        LOGIN_Expire(60 * 60 * 24 * 7),
        CAPTCHA_Expire(60 * 3);

        private long time;

        Expire(long time) {
            this.time = time;
        }

        public long getTime() {
            return time;
        }

        public void setTime(Long time) {
            this.time = time;
        }
    }
}
