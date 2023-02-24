package com.zifuji.cloud.server.sys.module.blog.mo;

import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.validation.constraints.NotBlank;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveBlogMo extends BaseMo {


    @NotBlank(message = "")
    private String blogName;

    @NotBlank(message = "")
    private String blogType;

    @NotBlank(message = "")
    private String blogContent;

    @NotBlank(message = "")
    private String blogText;

}
