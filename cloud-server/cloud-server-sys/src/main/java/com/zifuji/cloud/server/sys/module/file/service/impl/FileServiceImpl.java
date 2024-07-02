package com.zifuji.cloud.server.sys.module.file.service.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.zifuji.cloud.server.sys.module.file.service.bo.FileBo;
import com.zifuji.cloud.server.base.util.ZfjFileUtil;
import com.zifuji.cloud.server.sys.module.file.component.MinioComponent;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.base.module.exception.bean.Exception20000;
import com.zifuji.cloud.server.sys.db.file.entity.FileEntity;
import com.zifuji.cloud.server.sys.db.file.service.FileEntityService;
import com.zifuji.cloud.server.sys.module.file.controller.qo.FilePageQo;
import com.zifuji.cloud.server.sys.module.file.service.FileService;
import com.zifuji.cloud.server.sys.module.file.controller.vo.FileControllerVo;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Slf4j
@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private MinioComponent minioComponent;

    private FileEntityService fileEntityService;


    @Override
    public String uploadFile(MultipartFile file) {
        String fileUrl = minioComponent.uploadFile(file);
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileByteSize(file.getSize());
        fileEntity.setFileUrl(fileUrl);
        fileEntityService.save(fileEntity);
        return fileEntity.getId() + "";
    }

    @Override
    public FileBo downloadFileStream(String id) {
        FileEntity fileEntity = fileEntityService.getById(id);
        if (ObjectUtil.isNull(fileEntity)) {
            throw new Exception20000("找不到对应的数据");
        }
        InputStream inStream = minioComponent.downloadFile(fileEntity.getFileUrl());
        FileBo bo = new FileBo();
        bo.setFileName(fileEntity.getFileName());
        bo.setFileSize(fileEntity.getFileByteSize());
        bo.setInputStream(inStream);
        return bo;
    }

    @Override
    public FileControllerVo downloadFile(String id) throws IOException {
        FileControllerVo vo = new FileControllerVo();
        FileBo bo = downloadFileStream(id);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = bo.getInputStream().read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        vo.setFileName(bo.getFileName());
        vo.setFileByte(outStream.toByteArray());
        vo.setFileByteSize((long) outStream.size());
        vo.setMimeType(ZfjFileUtil.getMimeType(bo.getFileName()));
        return vo;
    }

    @Override
    public MultipartFile getFile(String id) {
        FileBo bo = downloadFileStream(id);
        FileItem fileItem = ZfjFileUtil.createFileItem(bo.getInputStream(), bo.getFileName());
        return new CommonsMultipartFile(fileItem);
    }

    @Override
    public List<MultipartFile> getFileList(List<String> fileIdList) {
        List<MultipartFile> fileList = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(fileIdList)) {
            for (String fileId : fileIdList) {
                fileList.add(getFile(fileId));
            }
        }
        return fileList;
    }

    @Override
    public String getFileName(String id) {
        FileEntity fileEntity = fileEntityService.getById(id);
        return fileEntity.getFileName();
    }

    @Override
    public Boolean delFile(String id) {
        FileEntity fileEntity = fileEntityService.getById(id);
        fileEntity.setDelFlag(true);
        return fileEntityService.updateById(fileEntity);
    }


    @Override
    public IPage<FileControllerVo> queryPageFile(FilePageQo filePageQo) {
        Page<FileEntity> page = new Page<FileEntity>(filePageQo.getCurrent(), filePageQo.getSize());
        QueryWrapper<FileEntity> queryWrapper = new QueryWrapper<FileEntity>();

        IPage<FileEntity> fileEntityPage = fileEntityService.page(page, queryWrapper);
        return fileEntityPage.convert(fileEntity -> {
            FileControllerVo fileVo = new FileControllerVo();
            BeanUtil.copyProperties(fileEntity, fileVo);
            return fileVo;
        });
    }
}