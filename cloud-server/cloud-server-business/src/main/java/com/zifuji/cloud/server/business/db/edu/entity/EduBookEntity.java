package com.zifuji.cloud.server.business.db.edu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_edu_book")
public class EduBookEntity extends MyBaseEntity {

    private String bName;

    private String isbn;

    private String bAuth;

    private String bType;

    private Long bImg;
}
