package com.harukaze.blog.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.blog.app.entity.BlogInfoEntity;
import com.harukaze.blog.app.vo.BlogInfoVo;

public interface BlogInfoService extends IService<BlogInfoEntity> {
    BlogInfoVo getinfo();
}
