package com.harukaze.blog.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.blog.app.entity.UserEntity;
import com.harukaze.blog.app.handler.exception.BlanckAuthorException;
import com.harukaze.blog.app.param.CommentParam;
import com.harukaze.blog.app.service.ThreadService;
import com.harukaze.blog.app.service.UserService;
import com.harukaze.blog.app.util.UserThreadLocal;
import com.harukaze.blog.app.vo.CommentVo;
import com.harukaze.blog.app.vo.UserVo;
import com.harukaze.blog.common.constant.CommentConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.common.utils.Query;

import com.harukaze.blog.app.dao.CommentDao;
import com.harukaze.blog.app.entity.CommentEntity;
import com.harukaze.blog.app.service.CommentService;

import javax.xml.stream.events.Comment;


@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentDao, CommentEntity> implements CommentService {

    @Autowired
    private UserService userService;

    @Autowired
    private ThreadService threadService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommentEntity> page = this.page(
                new Query<CommentEntity>().getPage(params),
                new QueryWrapper<CommentEntity>()
        );

        return new PageUtils(page);
    }

    // 根据文章id获取评论，首先获取顶端评论，再获取顶端评论的子评论
    @Override
    public PageUtils listCommentPage(Long articleId,  Map<String, Object> params) {
        // 首先获取顶端评论
        IPage<CommentEntity> page = this.page(
                new Query<CommentEntity>().getPage(params),
                new LambdaQueryWrapper<CommentEntity>()
                        .eq(CommentEntity::getArticleId, articleId)
                        .eq(CommentEntity::getLevel, 1)
                        .orderByDesc(CommentEntity::getWeight)
        );

        PageUtils pageUtils = new PageUtils(page);

        // 获取顶端评论的子评论
        List<CommentVo> collect = pageUtils.getList().stream().map(item -> {
            List<CommentVo> children = findChildren((CommentEntity) item);
            CommentVo commentVo = toVo((CommentEntity) item);
            commentVo.setChildren(children);
            return commentVo;
        }).collect(Collectors.toList());

        pageUtils.setList(collect);
        return pageUtils;
    }

    @Override
    public void saveComment(CommentParam param) {
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(param, commentEntity);

        commentEntity.setId(null);
        commentEntity.setCreateDate(System.currentTimeMillis());
        commentEntity.setState(CommentConstant.Status.ACTIVE.getCode());
        commentEntity.setWeight(1);
        if (commentEntity.getParentId() != null && commentEntity.getParentId() > 0) {
            commentEntity.setLevel(1);
        } else {
            commentEntity.setLevel(2);
        }

        // 如果用戶登录，将登录用户id存入评论作者id
        UserVo userVo = UserThreadLocal.get();
        if (userVo != null) {
            commentEntity.setAuthorId(userVo.getId());
        } else if (!"".equals(param.getAuthor().get("name"))){
            // 如果用户未登录，将传入的用户名写入评论用户名
            commentEntity.setAuthorName(param.getAuthor().get("name"));
        } else {
            throw new BlanckAuthorException("评论作者名不能为空");
        }


        if (!"".equals(param.getToUser().get("id")) && Long.parseLong(param.getToUser().get("id")) > 0) {
            UserEntity id = userService.getById("id");
            if (id == null) {
                // 如果被评论用户id有误则将传入被评论用户名写入数据库
                commentEntity.setToUserName(param.getToUser().get("name"));
            }
            commentEntity.setToUserId(Long.parseLong(param.getToUser().get("id")));
        } else {
            // 如果被评论用户没有账号（没有传入id）则将传入被评论用户名写入数据库
            commentEntity.setToUserName(param.getToUser().get("name"));
        }

        // 考虑到可能是留言的情况，留言的 ArticleId 为空
        if (commentEntity.getArticleId() != null && commentEntity.getArticleId() > 0) {
            threadService.updateArticleCommentCountsById(commentEntity.getArticleId());
        }

        this.baseMapper.insert(commentEntity);
    }

    @Override
    public void updateComment(CommentEntity comment) throws Exception {
        if (comment.getId() == null) {
            throw new Exception("见鬼你为什么不传id");
        }
        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setId(comment.getId());
        if (comment.getWeight() != null) {
            // 是否为置顶操作
            commentEntity.setWeight(
                    comment.getWeight() == CommentConstant.Status.ON_TOP.getCode() ?
                            comment.getWeight() : CommentConstant.Status.NAMAL.getCode());
        }
        if (comment.getState() != null) {
            // 是否为删除操作
            commentEntity.setState(
                    comment.getState() == CommentConstant.Status.DOWN.getCode() ?
                            comment.getState() : CommentConstant.Status.ACTIVE.getCode());
        }

        commentEntity.setContent(comment.getContent());
        this.baseMapper.updateById(commentEntity);
    }

    @Override
    public Object countComment(Long id) {
        LambdaQueryWrapper<CommentEntity> wrapper = new LambdaQueryWrapper<>();
        if (id == 0) {
            wrapper.isNull(CommentEntity::getArticleId);
        } else {
            wrapper.eq(CommentEntity::getArticleId, id);
        }
        return this.baseMapper.selectCount(wrapper);
    }

    private List<CommentVo> findChildren(CommentEntity parent) {
        List<CommentEntity> list = this.baseMapper.selectList(
                new LambdaQueryWrapper<CommentEntity>()
                        .eq(CommentEntity::getParentId, parent.getId()));

        return list.stream().map(this::toVo).collect(Collectors.toList());
    }

    private CommentVo toVo(CommentEntity commentEntity) {
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(commentEntity, commentVo);

        if (commentEntity.getAuthorId() != null && commentEntity.getAuthorId() > 0) {
            commentVo.setAuthor(userService.getUserVoById(commentEntity.getAuthorId()));
        } else {
            UserVo userVo = new UserVo();
            userVo.setNickname(commentEntity.getAuthorName());
            commentVo.setAuthor(userVo);
        }

        if (commentEntity.getToUserId() != null && commentEntity.getToUserId() > 0) {
            commentVo.setToUserNickname(userService.getById(commentEntity.getToUserId()).getNickname());
        } else {
            commentVo.setToUserNickname(commentEntity.getToUserName());
        }

        return commentVo;
    }

}