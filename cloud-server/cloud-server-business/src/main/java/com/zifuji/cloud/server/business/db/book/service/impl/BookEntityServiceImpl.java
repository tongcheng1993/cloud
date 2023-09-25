package com.zifuji.cloud.server.business.db.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.book.entity.EduBookEntity;
import com.zifuji.cloud.server.business.db.book.mapper.EduBookEntityMapper;
import com.zifuji.cloud.server.business.db.book.service.EduBookEntityService;
import org.springframework.stereotype.Service;

@Service
public class EduBookEntityServiceImpl extends ServiceImpl<EduBookEntityMapper, EduBookEntity> implements EduBookEntityService {
}
