package com.harukaze.blog.app.vo;

import com.harukaze.blog.app.entity.MenuEntity;
import com.harukaze.blog.app.entity.PermissionEntity;
import com.harukaze.blog.common.valid.AddGroup;
import com.harukaze.blog.common.valid.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @PackageName: com.harukaze.blog.app.vo
 * @ClassName: PermissionVo
 * @Description:
 * @Author: doki
 * @Date: 2022/6/13 8:16
 */

@Data
public class PermissionVo {

    private Long id;

    private String name;

    private List<PermissionVo> children;
}
