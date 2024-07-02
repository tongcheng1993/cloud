package com.zifuji.cloud.server.business.db.reservation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.reservation.entity.ReservationSettingSpeDayEntity;
import com.zifuji.cloud.server.business.db.reservation.mapper.ReservationSettingSpeWorkEntityMapper;
import com.zifuji.cloud.server.business.db.reservation.service.ReservationSettingSpeDayEntityService;
import org.springframework.stereotype.Service;

@Service
public class ReservationSettingSpeDayEntityServiceImpl extends ServiceImpl<ReservationSettingSpeWorkEntityMapper, ReservationSettingSpeDayEntity> implements ReservationSettingSpeDayEntityService {
}
