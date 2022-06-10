package com.harukaze.blog.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.blog.app.entity.PermissionEntity;
import com.harukaze.blog.app.entity.RolePermissionEntity;
import com.harukaze.blog.app.param.RoleParam;
import com.harukaze.blog.app.service.PermissionService;
import com.harukaze.blog.app.service.RolePermissionService;
import com.harukaze.blog.app.vo.RoleVo;
import com.harukaze.blog.common.utils.R;
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

import com.harukaze.blog.app.dao.RoleDao;
import com.harukaze.blog.app.entity.RoleEntity;
import com.harukaze.blog.app.service.RoleService;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RoleEntity> page = this.page(
                new Query<RoleEntity>().getPage(params),
                new QueryWrapper<RoleEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils listRolePage(Map<String, Object> params) {
        LambdaQueryWrapper<RoleEntity> wrapper = new LambdaQueryWrapper<>();
        String key = (String) params.get("key");

        if (!StrUtil.isBlank(key)) {
            wrapper.like(RoleEntity::getRoleName, key);
        }
        IPage<RoleEntity> page = this.page(
                new Query<RoleEntity>().getPage(params),
                wrapper
        );
        PageUtils pageUtils = new PageUtils(page);

        List<RoleVo> collect = pageUtils.getList().stream()
                .map(item -> toVo((RoleEntity) item))
                .collect(Collectors.toList());

        pageUtils.setList(collect);
        return pageUtils;
    }

    @Override
    public RoleVo getRoleById(Long id) {
        return toVo(this.getById(id));
    }

    @Override
    public void updateRole(RoleParam param) {
        if (param.getId() == null || param.getId() <= 0) {
            return;
        }
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(param, roleEntity);

        // 更新 role
        this.updateById(roleEntity);

        // 先将角色权限全删除,再将存在的权限插入表
        rolePermissionService.remove(
                new LambdaQueryWrapper<RolePermissionEntity>()
                        .eq(RolePermissionEntity::getRoleId, roleEntity.getId()));

        // 考虑到本项目规模较小，直接将权限全部查出来，与传入的权限id作比较。
        List<PermissionEntity> list = permissionService.list();
        List<Long> idList = list.stream().map(PermissionEntity::getId).collect(Collectors.toList());

        for (Long permissionId : param.getPermissions()) {
            if (idList.contains(permissionId)) {
                RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
                rolePermissionEntity.setRoleId(param.getId());
                rolePermissionEntity.setPermissionId(permissionId);
                rolePermissionService.save(rolePermissionEntity);
            }
        }
    }

    @Override
    public void saveRole(RoleEntity role) {
        role.setId(null);
        this.save(role);
    }

    private RoleVo toVo(RoleEntity roleEntity) {
        RoleVo roleVo = new RoleVo();

        BeanUtils.copyProperties(roleEntity, roleVo);

        List<PermissionEntity> list = rolePermissionService.getPermissionListByRoleId(roleEntity.getId());
        roleVo.setPermissions(list);
        return roleVo;
    }

}