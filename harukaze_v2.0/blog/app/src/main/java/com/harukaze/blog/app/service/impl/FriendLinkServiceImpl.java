package com.harukaze.blog.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.harukaze.blog.common.constant.FriendLinkConstant;
import org.springframework.beans.BeanUtils;
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

    @Override
    public PageUtils listLinkPage(Map<String, Object> params) {
        LambdaQueryWrapper<FriendLinkEntity> wrapper = new LambdaQueryWrapper<>();
        String key = (String) params.get("key");

        if (!StrUtil.isBlank(key)) {
            wrapper.like(FriendLinkEntity::getName, key);
        }

        IPage<FriendLinkEntity> page = this.page(
                new Query<FriendLinkEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void saveLink(FriendLinkEntity friendLink) {
        friendLink.setState(FriendLinkConstant.Status.ACTIVE.getCode());
        friendLink.setCreateDate(System.currentTimeMillis());

        this.baseMapper.insert(friendLink);
    }

    @Override
    public void updateLink(FriendLinkEntity param) {
        if (param.getState() != null) {
            param.setState(param.getState() == FriendLinkConstant.Status.ACTIVE.getCode() ?
                    param.getState() : FriendLinkConstant.Status.DOWN.getCode());
        }

        param.setCreateDate(null);

        this.baseMapper.updateById(param);
    }

    @Override
    public void removeLink(Long id) {
        FriendLinkEntity friendLinkEntity = new FriendLinkEntity();
        friendLinkEntity.setId(id);
        friendLinkEntity.setState(FriendLinkConstant.Status.DOWN.getCode());
    }

    @Override
    public void setLinkState(Long id, boolean flag) {
        this.baseMapper.update(null,
                new LambdaUpdateWrapper<FriendLinkEntity>()
                        .eq(FriendLinkEntity::getId, id).set(FriendLinkEntity::getState, flag ?
                        FriendLinkConstant.Status.ACTIVE.getCode() : FriendLinkConstant.Status.DOWN.getCode()));
    }

}