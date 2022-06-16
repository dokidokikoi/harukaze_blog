package com.harukaze.blog.app;

import com.baomidou.mybatisplus.annotation.TableId;
import com.harukaze.blog.app.dao.AnimeDao;
import com.harukaze.blog.app.entity.AnimeEntity;
import com.harukaze.blog.app.service.ArticleService;
import com.harukaze.blog.app.util.IpUtils;
import com.harukaze.blog.app.util.UserAgentUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName: com.harukaze.blog.app
 * @ClassName: AnimeTest
 * @Description:
 * @Author: doki
 * @Date: 2022/5/31 10:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnimeTest {
    @Autowired
    PasswordEncoder passwordEncoder;
}
