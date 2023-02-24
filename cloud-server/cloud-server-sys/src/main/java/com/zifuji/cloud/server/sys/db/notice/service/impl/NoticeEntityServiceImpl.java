package com.zifuji.cloud.server.sys.db.notice.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.notice.entity.NoticeEntity;
import com.zifuji.cloud.server.sys.db.notice.mapper.NoticeEntityMapper;
import com.zifuji.cloud.server.sys.db.notice.service.NoticeEntityService;


@Service
public class NoticeEntityServiceImpl extends ServiceImpl<NoticeEntityMapper, NoticeEntity>  implements NoticeEntityService {

}
