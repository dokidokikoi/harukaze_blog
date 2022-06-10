package com.harukaze.blog.common.constant;

/**
 * @PackageName: com.harukaze.blog.common.constant
 * @ClassName: FriendLinkConstant
 * @Description:
 * @Author: doki
 * @Date: 2022/6/10 12:23
 */

public class FriendLinkConstant {

    public enum Status {
        ACTIVE(0, "发布"),
        DOWN(1, "下撤");

        private int code;
        private String msg;

        Status(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
