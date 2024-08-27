package com.zifuji.cloud.server.business.db.book.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_book_section")
public class BookSectionEntity extends MyBaseEntity {


    private Long bookId;

    private Integer sectionNo;

    private String sectionName;


}
