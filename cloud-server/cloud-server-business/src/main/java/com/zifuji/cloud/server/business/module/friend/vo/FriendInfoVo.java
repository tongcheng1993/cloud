package com.zifuji.cloud.server.business.module.friend.vo;

import com.zifuji.cloud.base.bean.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FriendInfoVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String imgUrl;

    private String name;

    private String sex;

    private LocalDate birthday;

    private String birthProvince;

    private String birthCity;

    private String birthTown;

    private String birthAddr;

    private String province;

    private String city;

    private String town;

    private String addr;

    private String info;
}
