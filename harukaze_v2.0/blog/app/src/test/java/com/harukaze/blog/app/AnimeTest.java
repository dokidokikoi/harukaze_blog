package com.harukaze.blog.app;

import com.harukaze.blog.app.dao.AnimeDao;
import com.harukaze.blog.app.entity.AnimeEntity;
import com.harukaze.blog.app.service.ArticleService;
import com.harukaze.blog.app.util.IpUtils;
import com.harukaze.blog.app.util.UserAgentUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    AnimeDao animeDao;

    @Autowired
    ArticleService articleService;

    @Test
    public void testMybatis() {
        AnimeEntity animeEntity = animeDao.selectById(1);
        System.out.println(animeEntity);
    }

    @Test
    public void testGetArticleList() {
        Map<String, Object> params = new HashMap<>();
        params.put("key", "2");
        System.out.println(articleService.listArticlePage(params));
    }

    @Test
    public void testGetIpAddress() throws IOException {
//        System.out.println(IpUtils.getIpAddrNum("120.76.201.50"));
//        System.out.println(IpUtils.ipToString(IpUtils.getIpAddrNum("120.76.201.50")));
//        System.out.println(IpUtils.getIpAddress("120.76.201.50"));
//        System.out.println(IpUtils.getCityInfo("110.52.208.189"));
        System.out.println(new UserAgentUtils().parseOsAndBrowser("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.63 Safari/537.36"));
    }
}
