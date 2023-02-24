package com.zifuji.cloud.server.sys.db.blog.entity;

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

<<<<<<< HEAD
    private String blogType;

    private String blogContent;

    private String blogText;

=======
    private String blogContent;

>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df

}
