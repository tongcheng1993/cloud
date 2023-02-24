package com.zifuji.cloud.server.sys.module.file.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.zifuji.cloud.server.base.util.ZfjFileUtil;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.sys.db.file.entity.FileEntity;
import com.zifuji.cloud.server.sys.db.file.service.FileEntityService;
import com.zifuji.cloud.server.sys.module.file.component.FastdfsComponent;
import com.zifuji.cloud.server.sys.module.file.qo.FilePageQo;
import com.zifuji.cloud.server.sys.module.file.service.FileService;
import com.zifuji.cloud.server.sys.module.file.vo.FileVo;
import com.zifuji.cloud.starter.web.util.MyBatisPlusUtil;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Slf4j
@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private FastdfsComponent fastdfsComponent;

    private FileEntityService fileEntityService;

    @Override
    public String uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Long fileByteSize = file.getSize();
        String fileUrl = "";
        try {
            fileUrl = fastdfsComponent.uploadFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception200("上传文件失败");
        }
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(fileName);
        fileEntity.setFileByteSize(fileByteSize);
        fileEntity.setFileUrl(fileUrl);
        fileEntityService.save(fileEntity);
        return fileEntity.getId() + "";
    }

    @Override
    public InputStream downloadFileStream(Long id) {
        FileEntity fileEntity = fileEntityService.getById(id);
        return fastdfsComponent.download(fileEntity.getFileUrl());
    }

    @Override
    public FileVo downloadFile(Long id) throws IOException {
        FileVo vo = new FileVo();
        FileEntity fileEntity = fileEntityService.getById(id);
        InputStream is = fastdfsComponent.download(fileEntity.getFileUrl());
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = is.read(buf);
        while (len != -1) {
            os.write(buf, 0, len);
            len = is.read(buf);
        }
        vo.setFileName(fileEntity.getFileName());
        vo.setFileByte(os.toByteArray());
        return vo;
    }

    @Override
    public MultipartFile getFile(Long id) {
        FileItem fileItem = ZfjFileUtil.createFileItem(downloadFileStream(id), getFileName(id));
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
        fastdfsComponent.deleteFile(fileEntity.getFileUrl());
        return fileEntityService.removeById(id);
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
