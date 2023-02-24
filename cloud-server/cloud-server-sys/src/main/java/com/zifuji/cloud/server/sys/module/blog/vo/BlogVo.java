package com.zifuji.cloud.server.sys.module.blog.vo;

import com.zifuji.cloud.base.bean.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class BlogVo extends BaseVo {

    private String blogName;

    private String blogAuth;

<<<<<<< HEAD
    private String blogType;

    private String blogContent;

    private String blogText;
=======
    private String blogContent;

>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df

}
