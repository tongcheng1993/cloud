package com.zifuji.cloud.base.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String token;

    private String userName;

    private String type;

    private String email;

    private String phone;

    private List<String> roleCodeList;

    private List<String> permissionCodeList;


}
