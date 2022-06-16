package com.harukaze.blog.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.blog.app.dao.ArticleBodyDao;
import com.harukaze.blog.app.dao.BlogInfoDao;
import com.harukaze.blog.app.entity.ArticleBodyEntity;
import com.harukaze.blog.app.entity.BlogInfoEntity;
import com.harukaze.blog.app.service.ArticleBodyService;
import com.harukaze.blog.app.service.BlogInfoService;
import com.harukaze.blog.app.service.UserService;
import com.harukaze.blog.app.vo.BlogInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName: com.harukaze.blog.app.service.impl
 * @ClassName: BlogInfoServiceImpl
 * @Description:
 * @Author: doki
 * @Date: 2022/6/15 9:15
 */

@Service
public class BlogInfoServiceImpl extends ServiceImpl<BlogInfoDao, BlogInfoEntity> implements BlogInfoService {

    @Autowired
    private UserService userService;

    @Override
    public BlogInfoVo getinfo() {
        BlogInfoEntity blogInfo = this.baseMapper.selectList(null).get(0);

        BlogInfoVo blogInfoVo = new BlogInfoVo();
        BeanUtils.copyProperties(blogInfo, blogInfoVo);
        blogInfoVo.setHost(userService.getUserVoById(blogInfo.getHost()));

        return blogInfoVo;
    }
}
