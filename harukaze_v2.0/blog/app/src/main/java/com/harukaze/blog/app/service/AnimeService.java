package com.harukaze.blog.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.app.entity.AnimeEntity;

import java.util.Map;

/**
 * 
 *
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:36
 */
public interface AnimeService extends IService<AnimeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

