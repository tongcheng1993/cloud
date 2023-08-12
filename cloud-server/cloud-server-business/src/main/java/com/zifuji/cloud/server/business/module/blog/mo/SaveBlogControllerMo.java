package com.zifuji.cloud.server.business.module.blog.mo;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.validation.constraints.NotBlank;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveBlogControllerMo extends BaseControllerMo {


    private Long id;

    @NotBlank(message = "")
    private String blogName;

    @NotBlank(message = "")
    private String blogType;

    @NotBlank(message = "")
    private String blogContent;

    @NotBlank(message = "")
    private String blogText;

}
