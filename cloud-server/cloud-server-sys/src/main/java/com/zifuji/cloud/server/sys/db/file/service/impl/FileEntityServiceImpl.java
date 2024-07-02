package com.zifuji.cloud.server.sys.db.file.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.file.entity.FileEntity;
import com.zifuji.cloud.server.sys.db.file.mapper.FileEntityMapper;
import com.zifuji.cloud.server.sys.db.file.service.FileEntityService;

@Service
public class FileEntityServiceImpl  extends ServiceImpl<FileEntityMapper, FileEntity> implements FileEntityService{

}
