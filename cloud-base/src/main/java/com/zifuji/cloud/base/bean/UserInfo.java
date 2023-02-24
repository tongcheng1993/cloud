package com.zifuji.cloud.base.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

@Data
public class UserInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserInfo() {
        this.id = 0L;
        this.token = "";
        this.userName = "游客" + DateUtil.now();
        this.name = this.userName;
        this.type = "";
        this.email = "";
        this.phone = "";
        this.roleList = Collections.emptyList();
        this.permissionList = Collections.emptyList();
    }

    private Long id;

    private String token;

    private String userName;

    private String name;

    private String type;

    private String email;

    private String phone;

    private List<String> roleList;

    private List<String> permissionList;


}
