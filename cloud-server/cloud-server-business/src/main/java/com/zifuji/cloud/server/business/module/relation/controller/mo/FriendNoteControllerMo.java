package com.zifuji.cloud.server.business.module.relation.controller.mo;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FriendNoteControllerMo  {


    private Long friendId;

    private String name;

    private String sex;

    private LocalDate birthday;

}
