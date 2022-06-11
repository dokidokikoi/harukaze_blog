package com.harukaze.blog.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.blog.common.utils.PageUtils;
import com.harukaze.blog.app.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author doki
 * @email harukaze_doki@163.com
 * @date 2022-05-31 10:19:36
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils listCategoryPage(Map<String, Object> params);

    boolean removeCategoryById(Long id);

    List<Map<String, Object>> getCategoryData();
}

