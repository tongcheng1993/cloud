package com.zifuji.cloud.server.sys.module.user.bo;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class WebUserComponentMo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;



    private String userName;

    private String passWord;

    private String name;

    private String email;

    private String phone;


    private String type;

    private Long peopleId;

    private Long companyId;


}
