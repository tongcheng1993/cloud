package com.zifuji.cloud.server.sys.module.user.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.base.bean.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class WebUserVo extends BaseVo {

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
