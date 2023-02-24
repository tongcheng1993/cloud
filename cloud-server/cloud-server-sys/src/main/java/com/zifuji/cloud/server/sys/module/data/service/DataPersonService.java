package com.zifuji.cloud.server.sys.module.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.data.qo.PersonPageQo;
import com.zifuji.cloud.server.sys.module.data.vo.PersonVo;
import org.springframework.web.multipart.MultipartFile;

public interface DataPersonService {

	Boolean uploadPersonListFile(MultipartFile file);



	IPage<PersonVo> queryPagePerson(PersonPageQo personPageQo);
}
