package com.harukaze.blog.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.Query;

import com.harukaze.blog.app.dao.FriendLinkDao;
import com.harukaze.blog.app.entity.FriendLinkEntity;
import com.harukaze.blog.app.service.FriendLinkService;


@Service("friendLinkService")
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkDao, FriendLinkEntity> implements FriendLinkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FriendLinkEntity> page = this.page(
                new Query<FriendLinkEntity>().getPage(params),
                new QueryWrapper<FriendLinkEntity>()
        );

        return new PageUtils(page);
    }

}