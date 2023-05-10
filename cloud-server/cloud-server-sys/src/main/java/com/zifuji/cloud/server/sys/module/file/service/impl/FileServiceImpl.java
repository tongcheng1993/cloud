package com.zifuji.cloud.server.sys.module.file.service.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.zifuji.cloud.server.base.util.ZfjFileUtil;
import com.zifuji.cloud.server.sys.module.file.component.MinioComponent;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.sys.db.file.entity.FileEntity;
import com.zifuji.cloud.server.sys.db.file.service.FileEntityService;
import com.zifuji.cloud.server.sys.module.file.qo.FilePageQo;
import com.zifuji.cloud.server.sys.module.file.service.FileService;
import com.zifuji.cloud.server.sys.module.file.vo.FileVo;
import com.zifuji.cloud.server.base.util.MyBatisPlusUtil;

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
        return minioComponent.uploadFile(file);
    }

    @Override
    public String uploadFile(String uploadPath, MultipartFile file) {
        String fileUrl = uploadFile(file);
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileByteSize(file.getSize());
        fileEntity.setFileUrl(fileUrl);
        fileEntity.setUploadPath(uploadPath);
        fileEntityService.save(fileEntity);
        return fileEntity.getId() + "";
    }

    @Override
    public InputStream downloadFileStream(String fileUuid) {
        return minioComponent.downloadFile(fileUuid);
    }

    @Override
    public FileVo downloadFile(String uploadPath, Long id) throws IOException {
        FileVo vo = new FileVo();
        FileEntity fileEntity = fileEntityService.getById(id);
        if (ObjectUtil.isNull(fileEntity)) {
            throw new Exception200("找不到对应的数据");
        }
        log.info(uploadPath);
        log.info(fileEntity.getUploadPath());
        log.info("" + StrUtil.equals(uploadPath, fileEntity.getUploadPath()));
        if (!StrUtil.equals(uploadPath, fileEntity.getUploadPath())) {
            throw new Exception200("找不到对应的数据");
        }
        InputStream inStream = downloadFileStream(fileEntity.getFileUrl());
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        vo.setFileName(fileEntity.getFileName());
        vo.setFileByte(outStream.toByteArray());
        vo.setMimeType(ZfjFileUtil.getMimeType(fileEntity.getFileName()));
        return vo;
    }

    @Override
    public MultipartFile getFile(Long id) {
        FileEntity fileEntity = fileEntityService.getById(id);
        FileItem fileItem = ZfjFileUtil.createFileItem(downloadFileStream(fileEntity.getFileUrl()), getFileName(id));
        return new CommonsMultipartFile(fileItem);
    }

    @Override
    public List<MultipartFile> getFileList(List<Long> fileIdList) {
        List<MultipartFile> fileList = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(fileIdList)) {
            for (Long fileId : fileIdList) {
                fileList.add(getFile(fileId));
            }
        }
        return fileList;
    }

    @Override
    public String getFileName(Long id) {
        FileEntity fileEntity = fileEntityService.getById(id);
        return fileEntity.getFileName();
    }

    @Override
    public Boolean delFile(Long id) {
        FileEntity fileEntity = fileEntityService.getById(id);
        fileEntity.setDelFlag(true);
        return fileEntityService.updateById(fileEntity);
    }


    @Override
    public IPage<FileVo> queryPageFile(FilePageQo filePageQo) {
        Page<FileEntity> page = new Page<FileEntity>(filePageQo.getCurrent(), filePageQo.getSize());
        QueryWrapper<FileEntity> queryWrapper = new QueryWrapper<FileEntity>();
        MyBatisPlusUtil.orderWrapper(queryWrapper, filePageQo.getOrders());
        IPage<FileEntity> fileEntityPage = fileEntityService.page(page, queryWrapper);
        return fileEntityPage.convert(fileEntity -> {
            FileVo fileVo = new FileVo();
            BeanUtil.copyProperties(fileEntity, fileVo);
            return fileVo;
        });
    }
}