package com.harukaze.blog.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.harukaze.blog.app.core.annotation.AccessLimit;
import com.harukaze.blog.app.core.annotation.HasPermission;
import com.harukaze.blog.app.core.annotation.LogAnnotation;
import com.harukaze.blog.app.param.RoleParam;
import com.harukaze.blog.app.vo.RoleVo;
import com.harukaze.blog.common.constant.ResponseStatus;
import com.harukaze.blog.common.valid.AddGroup;
import com.harukaze.blog.common.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.harukaze.blog.app.entity.RoleEntity;
import com.harukaze.blog.app.service.RoleService;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.R;



/**
 * 
 *
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:36
 */
@RestController
@RequestMapping("app/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 列表
     * {
     *     key: "",
     *     page: 1,
     *     limit: 5
     * }
     */
    @AccessLimit
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = roleService.listRolePage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @AccessLimit
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		RoleVo role = roleService.getRoleById(id);

        return R.ok().put("data", role);
    }

    /**
     * 新增角色
     */
    @LogAnnotation(module = "角色", operator = "新增角色")
    @HasPermission("role:save")
    @AccessLimit
    @PostMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody RoleEntity role){
		roleService.saveRole(role);

        return R.ok();
    }

    /**
     * 修改
     */
    @LogAnnotation(module = "角色", operator = "修改角色")
    @HasPermission("role:update")
    @AccessLimit
    @PutMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody RoleParam param){
		roleService.updateRole(param);

        return R.ok();
    }

    /**
     * 分配权限
     */
    @LogAnnotation(module = "角色", operator = "分配权限")
    @HasPermission("role:update")
    @AccessLimit
    @PutMapping("/set_permission/{id}")
    public R setPermission(@PathVariable("id") Long id, @RequestBody Long[] ids){
        roleService.setPermission(id, ids);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id){
		boolean flag = roleService.removeRoleById(id);

        return flag ? R.ok() : R.error(ResponseStatus.ROLE_DELETE_ERR.getCode(), ResponseStatus.ROLE_DELETE_ERR.getMsg());
    }

    @GetMapping("/count")
    public R getTotalCount() {
        return R.ok().put("data", roleService.count());
    }

}
