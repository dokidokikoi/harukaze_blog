package com.harukaze.blog.common.constant;

/**
 * @PackageName: com.harukaze.blog.common.constant
 * @ClassName: CommentConstant
 * @Description:
 * @Author: doki
 * @Date: 2022/6/8 16:33
 */

public class CommentConstant {

    public enum Status {
        ACTIVE(0, "发布"),
        DOWN(1, "下撤"),
        ON_TOP(10, "置顶"),
        NAMAL(1,"不置顶");

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
