package com.harukaze.blog.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @PackageName: com.harukaze.blog.app
 * @ClassName: AppApplication
 * @Description:
 * @Author: doki
 * @Date: 2022/5/31 10:04
 */

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}
