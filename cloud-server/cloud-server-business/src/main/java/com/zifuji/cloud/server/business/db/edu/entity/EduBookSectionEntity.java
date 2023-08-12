package com.zifuji.cloud.server.business.db.edu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_edu_book_section")
public class EduBookSectionEntity extends MyBaseEntity {

    private Long bookId;

    private String sectionName;


}
