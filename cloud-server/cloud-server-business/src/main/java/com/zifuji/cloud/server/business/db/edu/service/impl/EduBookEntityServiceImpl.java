package com.zifuji.cloud.server.business.db.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.edu.entity.EduBookEntity;
import com.zifuji.cloud.server.business.db.edu.mapper.EduBookEntityMapper;
import com.zifuji.cloud.server.business.db.edu.service.EduBookEntityService;
import org.springframework.stereotype.Service;

@Service
public class EduBookEntityServiceImpl extends ServiceImpl<EduBookEntityMapper, EduBookEntity> implements EduBookEntityService {
}