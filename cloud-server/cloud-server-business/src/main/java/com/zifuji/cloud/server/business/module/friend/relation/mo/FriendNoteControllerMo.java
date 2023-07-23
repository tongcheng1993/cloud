package com.zifuji.cloud.server.business.module.friend.relation.mo;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FriendNoteControllerMo extends BaseControllerMo {


    private Long friendId;

    private String name;

    private String sex;

    private LocalDate birthday;

}