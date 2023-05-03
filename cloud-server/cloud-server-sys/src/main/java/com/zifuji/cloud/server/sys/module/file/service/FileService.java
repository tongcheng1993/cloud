package com.zifuji.cloud.server.sys.module.file.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.file.qo.FilePageQo;
import com.zifuji.cloud.server.sys.module.file.vo.FileVo;

public interface FileService {

    String uploadFile(MultipartFile file);

    String uploadFile(String uploadPath, MultipartFile file);

    InputStream downloadFileStream(String fileUuid);

    FileVo downloadFile(String uploadPath, Long id) throws IOException;

    MultipartFile getFile(Long id);

    List<MultipartFile> getFileList(List<Long> fileIdList);

    String getFileName(Long id);

    Boolean delFile(Long id);

    IPage<FileVo> queryPageFile(FilePageQo filePageQo);
}