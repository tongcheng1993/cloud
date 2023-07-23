package com.zifuji.cloud.server.sys.module.data.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.base.bean.controller.BaseControllerVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class PersonControllerVo extends BaseControllerVo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;

    private LocalDate birthday;

    private String sex;

    private String cardType;

    private String cardNumber;
}
