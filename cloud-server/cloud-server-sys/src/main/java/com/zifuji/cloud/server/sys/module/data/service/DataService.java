package com.zifuji.cloud.server.sys.module.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.data.qo.PersonPageQo;
import com.zifuji.cloud.server.sys.module.data.vo.PersonControllerVo;
import org.springframework.web.multipart.MultipartFile;

public interface DataService {

	Boolean uploadPersonListFile(MultipartFile file);

	Boolean addPerson(String name,String cardNumber);

	IPage<PersonControllerVo> queryPagePerson(PersonPageQo personPageQo);

	Boolean uploadPhoneListFile(MultipartFile file);
}
