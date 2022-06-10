package com.harukaze.blog.app.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.harukaze.blog.common.valid.AddGroup;
import com.harukaze.blog.common.valid.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @PackageName: com.harukaze.blog.app.param
 * @ClassName: TodoParam
 * @Description:
 * @Author: doki
 * @Date: 2022/6/10 11:57
 */
@Data
public class TodoParam {

    @NotNull(message = "修改，id不能为空", groups = UpdateGroup.class)
    private Long id;
    /**
     * 代办事项
     */
    @NotBlank(message = "修改，content不能为空", groups = UpdateGroup.class)
    private String content;
    /**
     * 是否完成
     */
    @NotNull(message = "修改，content不能为空", groups = UpdateGroup.class)
    private Boolean done;
}
