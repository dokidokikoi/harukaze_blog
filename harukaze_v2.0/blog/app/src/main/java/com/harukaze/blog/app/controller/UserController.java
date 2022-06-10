package com.harukaze.blog.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.harukaze.blog.app.core.annotation.AccessLimit;
import com.harukaze.blog.app.core.annotation.HasPermission;
import com.harukaze.blog.app.core.annotation.LogAnnotation;
import com.harukaze.blog.app.vo.UserVo;
import com.harukaze.blog.common.valid.AddGroup;
import com.harukaze.blog.common.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.harukaze.blog.app.entity.UserEntity;
import com.harukaze.blog.app.service.UserService;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.R;



/**
 * 
 *
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:17
 */
@RestController
@RequestMapping("app/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     * {
     * 	"key": "",
     * 	"page": 1,
     * 	"limit": 5
     * }
     */
    @HasPermission("user:select")
    @AccessLimit
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.listUserPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
//    @AccessLimit
//    @GetMapping("/info/{id}")
//    public R info(@PathVariable("id") Long id){
//		UserVo user = userService.getUserVoById(id);
//
//        return R.ok().put("data", user);
//    }

    /**
    * 获取当前用户信息
    */
    @AccessLimit
    @GetMapping("/info")
    public R info(){
		UserVo user = userService.getCurrentUser();

        return R.ok().put("data", user);
    }

    /**
     * 保存
     */
    @LogAnnotation(module = "用户", operator = "新增用户")
    @HasPermission("user:add")
    @AccessLimit
    @PostMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody UserEntity user){
		userService.saveUser(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @LogAnnotation(module = "用户", operator = "修改用户")
    @HasPermission("user:update")
    @AccessLimit
    @PutMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody UserEntity user){
		userService.updateUserById(user);

        return R.ok();
    }

    /**
     * 删除
     */
//    @RequestMapping("/delete")
//    public R delete(@RequestBody Long[] ids){
//		userService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }

}
