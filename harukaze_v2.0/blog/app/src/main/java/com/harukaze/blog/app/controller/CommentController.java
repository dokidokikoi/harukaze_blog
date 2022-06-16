package com.harukaze.blog.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.harukaze.blog.app.core.annotation.AccessLimit;
import com.harukaze.blog.app.core.annotation.HasPermission;
import com.harukaze.blog.app.core.annotation.LogAnnotation;
import com.harukaze.blog.app.handler.exception.ArticleNotFoundException;
import com.harukaze.blog.app.param.CommentParam;
import com.harukaze.blog.common.valid.AddGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.harukaze.blog.app.entity.CommentEntity;
import com.harukaze.blog.app.service.CommentService;
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
@RequestMapping("app/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 列表
     * 根据文章id获取文章评论列表
     * 如果文章id为0，则是留言
     */
    @GetMapping("/list/{articleId}")
    public R list(@PathVariable("articleId") Long articleId,@RequestParam Map<String, Object> params) throws ArticleNotFoundException {
        PageUtils page = commentService.listCommentPage(articleId, params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
//    @GetMapping("/info/{id}")
//    public R info(@PathVariable("id") Long id){
//		CommentEntity comment = commentService.getById(id);
//
//        return R.ok().put("data", comment);
//    }

    /**
     * 发表评论
     */
    @LogAnnotation(module = "评论", operator = "发表评论")
    @AccessLimit(isMustLogin = false)
    @PostMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody CommentParam param){
		commentService.saveComment(param);

        return R.ok();
    }

    /**
     * 修改
     */
    @LogAnnotation(module = "评论", operator = "修改评论")
    @HasPermission("comment:update")
    @AccessLimit
    @PutMapping("/update")
    public R update(@Validated @RequestBody CommentEntity comment) throws Exception {
		commentService.updateComment(comment);

        return R.ok();
    }

    @LogAnnotation(module = "评论", operator = "修改评论状态")
    @HasPermission("comment:update")
    @AccessLimit
    @PutMapping("/set_state")
    public R setState(Long id, boolean flag) {
        commentService.setCommentState(id, flag);

        return R.ok();
    }

    /**
     * 删除
     */
//    @AccessLimit
//    @DeleteMapping("/delete")
//    public R delete(@RequestBody Long[] ids){
//		commentService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }

    @GetMapping("/count/{id}")
    public R getTotalCountByArticleId(@PathVariable("id") Long id) {
        return R.ok().put("data", commentService.countComment(id));
    }

    @GetMapping("/count")
    public R getTotalCount() {
        return R.ok().put("data", commentService.count());
    }

}
