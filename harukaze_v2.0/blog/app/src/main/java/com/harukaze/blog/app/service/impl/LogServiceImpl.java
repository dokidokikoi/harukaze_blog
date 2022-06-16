package com.harukaze.blog.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.blog.app.util.IpUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.Query;

import com.harukaze.blog.app.dao.LogDao;
import com.harukaze.blog.app.entity.LogEntity;
import com.harukaze.blog.app.service.LogService;


@Service("logService")
public class LogServiceImpl extends ServiceImpl<LogDao, LogEntity> implements LogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogEntity> page = this.page(
                new Query<LogEntity>().getPage(params),
                new QueryWrapper<LogEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils listLogPage(Map<String, Object> params) {
        LambdaQueryWrapper<LogEntity> wrapper = new LambdaQueryWrapper<>();
        String key = (String) params.get("key");
        Long ip = null;
        try {
            ip = IpUtils.getIpAddrNum(key);
        } catch (Exception e) {

        }

        if (!StrUtil.isBlank(key)) {
            Long finalIp = ip;
            wrapper.and(w -> {
                w.like(LogEntity::getModule, key)
                        .or()
                        .like(LogEntity::getOperation, key);
            });
        }

        IPage<LogEntity> page = this.page(
                new Query<LogEntity>().getPage(params),
                wrapper.orderByDesc(LogEntity::getCreateDate)
        );

        return new PageUtils(page);
    }

}