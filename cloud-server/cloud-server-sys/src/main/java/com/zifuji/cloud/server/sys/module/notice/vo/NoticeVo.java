package com.zifuji.cloud.server.sys.module.notice.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class NoticeVo {


	private String id;

	private String createBy;

	
	private String title;

}
