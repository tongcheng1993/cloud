package com.zifuji.cloud.server.business.db.school.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.school.entity.EduSchoolEntity;
import com.zifuji.cloud.server.business.db.school.mapper.EduSchoolEntityMapper;
import com.zifuji.cloud.server.business.db.school.service.EduSchoolEntityService;
import org.springframework.stereotype.Service;

@Service
public class EduSchoolEntityServiceImpl extends ServiceImpl<EduSchoolEntityMapper, EduSchoolEntity> implements EduSchoolEntityService {
}
