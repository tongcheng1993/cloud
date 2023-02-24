package com.zifuji.cloud.server.sys.module.blog.mo;

import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

<<<<<<< HEAD
import javax.validation.constraints.NotBlank;

=======
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveBlogMo extends BaseMo {

<<<<<<< HEAD
    @NotBlank(message = "")
    private String blogName;

    @NotBlank(message = "")
    private String blogType;

    @NotBlank(message = "")
    private String blogContent;

    @NotBlank(message = "")
    private String blogText;
=======
    private Long id;

    private String blogName;

    private String blogAuth;

    private String blogContent;
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
}
