package com.zifuji.cloud.server.sys.module.reservation.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.sys.db.reservation.entity.ReservationSettingMainEntity;
import com.zifuji.cloud.server.sys.db.reservation.entity.ReservationSettingSpeRestEntity;
import com.zifuji.cloud.server.sys.db.reservation.entity.ReservationSettingSpeWorkEntity;
import com.zifuji.cloud.server.sys.db.reservation.entity.ReservationSettingWorkDayEntity;
import com.zifuji.cloud.server.sys.db.reservation.service.ReservationSettingMainEntityService;
import com.zifuji.cloud.server.sys.db.reservation.service.ReservationSettingSpeRestEntityService;
import com.zifuji.cloud.server.sys.db.reservation.service.ReservationSettingSpeWorkEntityService;
import com.zifuji.cloud.server.sys.db.reservation.service.ReservationSettingWorkDayEntityService;
import com.zifuji.cloud.server.sys.module.reservation.bo.ReservationDayStatusBo;
import com.zifuji.cloud.server.sys.module.reservation.mo.ReservationSettingMainMo;
import com.zifuji.cloud.server.sys.module.reservation.service.ReservationService;
import com.zifuji.cloud.server.sys.module.reservation.vo.ReservationSettingMainVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {


    private ReservationSettingMainEntityService reservationSettingMainEntityService;

    private ReservationSettingWorkDayEntityService reservationSettingWorkDayEntityService;

    private ReservationSettingSpeWorkEntityService reservationSettingSpeWorkEntityService;

    private ReservationSettingSpeRestEntityService reservationSettingSpeRestEntityService;

    @Override
    public ReservationSettingMainVo saveReservationSettingMain(ReservationSettingMainMo reservationSettingMainMo) {
        ReservationSettingMainVo vo = new ReservationSettingMainVo();
        if (ObjectUtil.isNull(reservationSettingMainMo.getId())) {
            ReservationSettingMainEntity reservationSettingMainEntity = new ReservationSettingMainEntity();
            BeanUtil.copyProperties(reservationSettingMainMo, reservationSettingMainEntity);
            reservationSettingMainEntityService.save(reservationSettingMainEntity);
            BeanUtil.copyProperties(reservationSettingMainEntity, vo);

        } else {
            ReservationSettingMainEntity reservationSettingMainEntity = reservationSettingMainEntityService.getById(reservationSettingMainMo.getId());
            BeanUtil.copyProperties(reservationSettingMainMo, reservationSettingMainEntity);
            reservationSettingMainEntityService.updateById(reservationSettingMainEntity);
            BeanUtil.copyProperties(reservationSettingMainEntity, vo);
        }
        return vo;
    }

    @Override
    public ReservationSettingMainVo getDefaultReservationSetting() {
        return getReservationSetting("default");
    }

    @Override
    public ReservationSettingMainVo getReservationSetting(String code) {
        ReservationSettingMainVo vo = new ReservationSettingMainVo();
        // 查看code对应的配置主表记录
        QueryWrapper<ReservationSettingMainEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ReservationSettingMainEntity::getCode, code);
        ReservationSettingMainEntity reservationSettingMainEntity = reservationSettingMainEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(reservationSettingMainEntity)) {
            throw new Exception200("code:" + code + "没有对应的配置主表记录");
        }
        QueryWrapper<ReservationSettingWorkDayEntity> reservationSettingWorkDayEntityQueryWrapper = new QueryWrapper<>();
        reservationSettingWorkDayEntityQueryWrapper.lambda().eq(ReservationSettingWorkDayEntity::getSettingMainId, reservationSettingMainEntity.getId());
        List<ReservationSettingWorkDayEntity> reservationSettingWorkDayEntityList = reservationSettingWorkDayEntityService.list(reservationSettingWorkDayEntityQueryWrapper);
        if (!(8 == reservationSettingWorkDayEntityList.size())) {
            throw new Exception200("code:" + code + "配置工作日信息有误");
        }
        Date startDate = DateUtil.date();
        Date endDate = DateUtil.offsetDay(startDate, reservationSettingMainEntity.getOpenDays() - 1);
        QueryWrapper<ReservationSettingSpeWorkEntity> reservationSettingSpeWorkEntityQueryWrapper = new QueryWrapper<>();
        reservationSettingSpeWorkEntityQueryWrapper.lambda().eq(ReservationSettingSpeWorkEntity::getSettingMainId, reservationSettingMainEntity.getId())
                .ge(ReservationSettingSpeWorkEntity::getSpeWordDay, startDate)
                .le(ReservationSettingSpeWorkEntity::getSpeWordDay, endDate);
        List<ReservationSettingSpeWorkEntity> reservationSettingSpeWorkEntityList = reservationSettingSpeWorkEntityService.list(reservationSettingSpeWorkEntityQueryWrapper);
        if (ObjectUtil.isEmpty(reservationSettingSpeWorkEntityList)) {
            log.info("code:" + code + "在开始时间" + startDate + "结束时间" + endDate + "没有配置特殊工作日");
        }
        QueryWrapper<ReservationSettingSpeRestEntity> reservationSettingSpeRestEntityQueryWrapper = new QueryWrapper<>();
        reservationSettingSpeRestEntityQueryWrapper.lambda().eq(ReservationSettingSpeRestEntity::getSettingMainId, reservationSettingMainEntity.getId())
                .ge(ReservationSettingSpeRestEntity::getSpeRestDay, startDate)
                .le(ReservationSettingSpeRestEntity::getSpeRestDay, endDate);
        List<ReservationSettingSpeRestEntity> reservationSettingSpeRestEntityList = reservationSettingSpeRestEntityService.list(reservationSettingSpeRestEntityQueryWrapper);
        if (ObjectUtil.isEmpty(reservationSettingSpeRestEntityList)) {
            log.info("code:" + code + "在开始时间" + startDate + "结束时间" + endDate + "没有配置特殊休息日");
        }

        List<ReservationDayStatusBo> list = new ArrayList<>();
        for (int i = 0; i < reservationSettingMainEntity.getOpenDays(); i++) {
            Date date = DateUtil.date();
            date = DateUtil.offsetDay(date, i);
            String formatDate = DateUtil.formatDate(date);
            Week week = DateUtil.dayOfWeekEnum(date);
            ReservationDayStatusBo bo = new ReservationDayStatusBo();
            bo.setDayName(formatDate);
            for (ReservationSettingWorkDayEntity reservationSettingWorkDayEntity : reservationSettingWorkDayEntityList) {
                if (week.getValue() == reservationSettingWorkDayEntity.getDayNo()) {
                    bo.setDayStatus(reservationSettingWorkDayEntity.getWorkFlag());
                }
            }
            list.add(bo);
        }


        return vo;
    }


}
