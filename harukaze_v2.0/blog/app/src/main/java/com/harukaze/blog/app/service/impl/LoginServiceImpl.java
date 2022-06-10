package com.harukaze.blog.app.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.harukaze.blog.app.entity.UserEntity;
import com.harukaze.blog.app.param.LoginParam;
import com.harukaze.blog.app.service.LoginService;
import com.harukaze.blog.app.service.ThreadService;
import com.harukaze.blog.app.service.UserService;
import com.harukaze.blog.common.constant.LoginConstant;
import com.harukaze.blog.common.utils.JwtUtils;
import com.harukaze.blog.common.utils.R;
import com.harukaze.blog.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @PackageName: com.harukaze.blog.app.service.impl
 * @ClassName: LoginServiceImpl
 * @Description:
 * @Author: doki
 * @Date: 2022/6/7 11:12
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ThreadService threadService;

    @Override
    public R getCaptcha() {
        //生成文字验证码
        String key = UUID.randomUUID().toString();
        String code = defaultKaptcha.createText();
        redisUtil.set("captcha_" + key, code, LoginConstant.Expire.CAPTCHA_Expire.getTime());

        //生成图片验证码
        BufferedImage image = defaultKaptcha.createImage(code);
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();

        try {
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> map = new HashMap<>();
        map.put("imgCode", "data:image/jpeg;base64," + Base64.encode(os.toByteArray()));
        map.put("key", key);

        return R.ok().put("data", map);
    }

    @Override
    public R login(LoginParam loginParam) {
        String code = (String) redisUtil.get("captcha_" + loginParam.getKey());
        if (StrUtil.isBlank(code) || !loginParam.getCode().equals(code)) {
            return R.error(HttpStatus.HTTP_BAD_REQUEST, "验证码错误");
        }

        UserEntity user = userService.getOne(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getAccount, loginParam.getAccount()));
        if (user == null) {
            return R.error(HttpStatus.HTTP_BAD_REQUEST, "用户不存在");
        }

        if (!passwordEncoder.matches(loginParam.getPassword(), user.getPassword())) {
            return R.error(HttpStatus.HTTP_BAD_REQUEST, "密码错误");
        }
        // 生成 token，设置登录失效时间
        String token = JwtUtils.generateToken(loginParam.getAccount(), LoginConstant.Expire.LOGIN_Expire.getTime());

        // 将 token 存入 redis，设置登录失效时间
        redisUtil.set("token_" + token, loginParam.getAccount(), LoginConstant.Expire.LOGIN_Expire.getTime());

        // 将验证码信息从 rides 删除
        redisUtil.del("captcha_"+loginParam.getKey());

        // 将数据封装到 map 中
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("user_info", userService.toVo(user));

        // 更新用户最近一次登录
        threadService.updateUserLastLogin(user.getId());

        return R.ok("登录成功").put("data", map);
    }

    @Override
    public R logout(HttpServletRequest request) {
        redisUtil.del("token_"+request.getHeader(JwtUtils.getHeader()));
        return R.ok();
    }


}
