package com.harukaze.blog.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.Query;

import com.harukaze.blog.app.dao.AnimeDao;
import com.harukaze.blog.app.entity.AnimeEntity;
import com.harukaze.blog.app.service.AnimeService;


@Service("animeService")
public class AnimeServiceImpl extends ServiceImpl<AnimeDao, AnimeEntity> implements AnimeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AnimeEntity> page = this.page(
                new Query<AnimeEntity>().getPage(params),
                new QueryWrapper<AnimeEntity>()
        );

        return new PageUtils(page);
    }

}