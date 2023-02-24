package com.zifuji.cloud.server.sys.module.data.service;

import org.springframework.web.multipart.MultipartFile;

public interface DataPhoneService {

    Boolean uploadPhoneListFile(MultipartFile file);
}
