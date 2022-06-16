package com.harukaze.blog.app.controller;

import java.util.Map;

import com.harukaze.blog.app.core.annotation.AccessLimit;
import com.harukaze.blog.app.core.annotation.HasPermission;
import com.harukaze.blog.app.core.annotation.LogAnnotation;
import com.harukaze.blog.common.valid.AddGroup;
import com.harukaze.blog.common.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.harukaze.blog.app.entity.FriendLinkEntity;
import com.harukaze.blog.app.service.FriendLinkService;
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
@RequestMapping("app/friendlink")
public class FriendLinkController {
    @Autowired
    private FriendLinkService friendLinkService;

    /**
     * 列表
     * {
     *     key: "",
     *     page: 1,
     *     limit: 5
     * }
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = friendLinkService.listLinkPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
//    @GetMapping("/info/{id}")
//    public R info(@PathVariable("id") Long id){
//		FriendLinkEntity friendLink = friendLinkService.getById(id);
//
//        return R.ok().put("data", friendLink);
//    }

    /**
     * 新增友链
     */
    @LogAnnotation(module = "友链", operator = "新增友链")
    @HasPermission("link:add")
    @AccessLimit
    @PostMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody FriendLinkEntity friendLink){
		friendLinkService.saveLink(friendLink);

        return R.ok();
    }

    /**
     * 修改
     */
    @LogAnnotation(module = "友链", operator = "修改友链")
    @HasPermission("link:update")
    @AccessLimit
    @PutMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody FriendLinkEntity param){
		friendLinkService.updateLink(param);

        return R.ok();
    }

    @LogAnnotation(module = "友链", operator = "修改友链状态")
    @HasPermission("link:update")
    @AccessLimit
    @PutMapping("/set_state")
    public R setState(Long id, boolean flag){
        friendLinkService.setLinkState(id, flag);

        return R.ok();
    }

    /**
     * 删除
     */
//    @LogAnnotation(module = "友链", operator = "删除友链")
//    @HasPermission("link:delete")
//    @AccessLimit
//    @DeleteMapping("/delete/{id}")
//    public R delete(@PathVariable("id") Long id){
//		friendLinkService.removeLink(id);
//
//        return R.ok();
//    }

    @GetMapping("/count")
    public R getTotalCount() {
        return R.ok().put("data", friendLinkService.count());
    }

}
