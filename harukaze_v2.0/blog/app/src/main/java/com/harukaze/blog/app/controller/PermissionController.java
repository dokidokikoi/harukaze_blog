package com.harukaze.blog.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.harukaze.blog.app.core.annotation.AccessLimit;
import com.harukaze.blog.app.core.annotation.HasPermission;
import com.harukaze.blog.app.core.annotation.LogAnnotation;
import com.harukaze.blog.common.valid.AddGroup;
import com.harukaze.blog.common.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.harukaze.blog.app.entity.PermissionEntity;
import com.harukaze.blog.app.service.PermissionService;
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
@RequestMapping("app/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 列表
     * {
     * 	"key": "",
     * 	"page": 1,
     * 	"limit": 10
     * }
     */
    @AccessLimit
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = permissionService.listPermissionPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @AccessLimit
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		PermissionEntity permission = permissionService.getById(id);

        return R.ok().put("data", permission);
    }

    /**
     * 新增权限
     */
    @LogAnnotation(module = "权限", operator = "新增权限")
    @HasPermission("permission:save")
    @AccessLimit
    @PostMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody PermissionEntity permission){
		permissionService.savePermission(permission);

        return R.ok();
    }

    /**
     * 修改
     */
    @LogAnnotation(module = "权限", operator = "修改权限")
    @HasPermission("permission:update")
    @AccessLimit
    @PutMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody PermissionEntity permission){
		permissionService.updateById(permission);

        return R.ok();
    }

    /**
     * 删除
     */
//    @RequestMapping("/delete")
//    public R delete(@RequestBody Long[] ids){
//		permissionService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }

    @GetMapping("/count")
    public R getTotalCount() {
        return R.ok().put("data", permissionService.count());
    }

}
