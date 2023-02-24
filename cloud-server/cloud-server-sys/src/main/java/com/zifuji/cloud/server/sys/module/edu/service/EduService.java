package com.zifuji.cloud.server.sys.module.edu.service;

import org.springframework.web.multipart.MultipartFile;

public interface EduService {

    Boolean uploadFile(MultipartFile file);
}
