package com.harukaze.blog.common.constant;

/**
 * @PackageName: com.harukaze.blog.common.constant
 * @ClassName: ToDoConstant
 * @Description:
 * @Author: doki
 * @Date: 2022/6/10 9:59
 */

public class ToDoConstant {

    public enum Status {
        NEW(0, "新建"),
        DONE(1, "完成");

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
