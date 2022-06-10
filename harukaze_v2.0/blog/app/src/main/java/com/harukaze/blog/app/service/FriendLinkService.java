package com.harukaze.blog.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.app.entity.FriendLinkEntity;

import java.util.Map;

/**
 * 
 *
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:36
 */
public interface FriendLinkService extends IService<FriendLinkEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils listLinkPage(Map<String, Object> params);

    void saveLink(FriendLinkEntity friendLink);

    void updateLink(FriendLinkEntity param);

    void removeLink(Long id);
}

