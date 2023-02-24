package com.zifuji.cloud.server.sys.db.notice.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.notice.entity.NoticeReadEntity;
import com.zifuji.cloud.server.sys.db.notice.mapper.NoticeReadEntityMapper;
import com.zifuji.cloud.server.sys.db.notice.service.NoticeReadEntityService;

@Service
public class NoticeReadEntityServiceImpl extends ServiceImpl<NoticeReadEntityMapper, NoticeReadEntity> implements NoticeReadEntityService{

}
