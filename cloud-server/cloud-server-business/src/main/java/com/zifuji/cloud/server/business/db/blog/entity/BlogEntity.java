package com.zifuji.cloud.server.business.db.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_blog")
public class BlogEntity  extends MyBaseEntity {



    private String blogName;

    private String blogAuth;


    private String blogType;

    private String blogContent;

    private String blogText;



}
