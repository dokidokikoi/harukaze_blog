package com.harukaze.blog.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.blog.app.vo.UserVo;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.app.entity.UserEntity;

import java.util.Map;

/**
 * 
 *
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:17
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    UserVo toVo(UserEntity user);

    UserVo getUserVoById(Long id);

    PageUtils listUserPage(Map<String, Object> params);

    void saveUser(UserEntity user);

    void updateUserById(UserEntity user);

    UserVo getCurrentUser();

    void updateLastLogin(Long id);
}

