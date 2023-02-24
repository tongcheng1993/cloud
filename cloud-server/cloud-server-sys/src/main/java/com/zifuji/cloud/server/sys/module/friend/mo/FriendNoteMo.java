package com.zifuji.cloud.server.sys.module.friend.mo;

import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FriendNoteMo extends BaseMo {


    private Long friendId;

    private String name;

    private String sex;

    private LocalDate birthday;

}
