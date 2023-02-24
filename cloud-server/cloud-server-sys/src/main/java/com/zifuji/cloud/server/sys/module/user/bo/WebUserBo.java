package com.zifuji.cloud.server.sys.module.user.bo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zifuji.cloud.base.bean.BaseBo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class WebUserBo extends BaseBo {
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
