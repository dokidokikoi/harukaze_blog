package com.harukaze.blog.app;

import com.harukaze.blog.app.dao.AnimeDao;
import com.harukaze.blog.app.entity.AnimeEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void testMybatis() {
        AnimeEntity animeEntity = animeDao.selectById(1);
        System.out.println(animeEntity);
    }
}
