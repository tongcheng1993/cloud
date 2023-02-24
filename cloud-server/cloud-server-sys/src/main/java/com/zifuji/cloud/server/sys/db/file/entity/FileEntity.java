package com.zifuji.cloud.server.sys.db.file.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_file")
public class FileEntity extends MyBaseEntity {
	// 文件名称
	private String fileName = "";
	// 文件大小
	private Long fileByteSize = 0L;
	// 文件地址
	private String fileUrl = "";
}
