package com.zifuji.cloud.server.business.module.reservation.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.business.db.reservation.entity.*;
import com.zifuji.cloud.server.business.db.reservation.service.*;
import com.zifuji.cloud.server.business.module.reservation.bo.ReservationDayBo;
import com.zifuji.cloud.server.business.module.reservation.bo.ReservationTimeBo;
import com.zifuji.cloud.server.business.module.reservation.mo.*;
import com.zifuji.cloud.server.business.module.reservation.service.ReservationService;
import com.zifuji.cloud.server.business.module.reservation.vo.ReservationApplyMainControllerVo;
import com.zifuji.cloud.server.business.module.reservation.vo.ReservationApplyWorkDayControllerVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private StringRedisTemplate stringRedisTemplate;

    private ReservationApplyRecordEntityService reservationApplyRecordEntityService;

    private ReservationSettingMainEntityService reservationSettingMainEntityService;

    private ReservationSettingWorkDayEntityService reservationSettingWorkDayEntityService;

    private ReservationSettingSpeDayEntityService reservationSettingSpeDayEntityService;

    private ReservationSettingWorkTimeEntityService reservationSettingWorkTimeEntityService;

    private ReservationSettingSpeTimeEntityService reservationSettingSpeTimeEntityService;

    private ReservationSettingDayRecordEntityService reservationSettingDayRecordEntityService;

    private ReservationSettingTimeRecordEntityService reservationSettingTimeRecordEntityService;

    @Override
    public ReservationApplyMainControllerVo saveReservationSettingMain(ReservationSettingMainControllerMo reservationSettingMainMo) {
        // 判断星期工作日设置的正确性
        if (!(7 == reservationSettingMainMo.getReservationSettingWorkDayMoList().size())) {
            throw new Exception200("code:" + reservationSettingMainMo.getCode() + "配置星期工作日信息有误");
        }
        ReservationApplyMainControllerVo vo = new ReservationApplyMainControllerVo();
        if (ObjectUtil.isNull(reservationSettingMainMo.getId())) {
            ReservationSettingMainEntity reservationSettingMainEntity = new ReservationSettingMainEntity();
            BeanUtil.copyProperties(reservationSettingMainMo, reservationSettingMainEntity);
            reservationSettingMainEntityService.save(reservationSettingMainEntity);
            for (ReservationSettingWorkDayMo reservationSettingWorkDayMo : reservationSettingMainMo.getReservationSettingWorkDayMoList()) {
                ReservationSettingWorkDayEntity reservationSettingWorkDayEntity = new ReservationSettingWorkDayEntity();
                reservationSettingWorkDayEntity.setSettingMainId(reservationSettingMainEntity.getId());
                reservationSettingWorkDayEntity.setDayNo(reservationSettingWorkDayMo.getDayNo());
                reservationSettingWorkDayEntity.setWorkFlag(reservationSettingWorkDayMo.getWorkFlag());
                reservationSettingWorkDayEntityService.save(reservationSettingWorkDayEntity);
            }
            for (ReservationSettingWorkTimeMo reservationSettingWorkTimeMo : reservationSettingMainMo.getReservationSettingWorkTimeMoList()) {
                ReservationSettingWorkTimeEntity reservationSettingWorkTimeEntity = new ReservationSettingWorkTimeEntity();
                reservationSettingWorkTimeEntity.setSettingMainId(reservationSettingMainEntity.getId());
                reservationSettingWorkTimeEntity.setStartRange(reservationSettingWorkTimeMo.getStartRange());
                reservationSettingWorkTimeEntity.setEndRange(reservationSettingWorkTimeMo.getEndRange());
                reservationSettingWorkTimeEntity.setAllNum(reservationSettingWorkTimeMo.getAllNum());
                reservationSettingWorkTimeEntityService.save(reservationSettingWorkTimeEntity);
            }
//            for (ReservationSettingSpeDayMo reservationSettingSpeDayMo : reservationSettingMainMo.getReservationSettingSpeDayMoList()) {
//                ReservationSettingSpeDayEntity reservationSettingSpeDayEntity = new ReservationSettingSpeDayEntity();
//                reservationSettingSpeDayEntity.setSettingMainId(reservationSettingMainEntity.getId());
//                reservationSettingSpeDayEntity.setSpeDay(reservationSettingSpeDayMo.getSpeDay());
//                reservationSettingSpeDayEntity.setWorkFlag(reservationSettingSpeDayMo.getWorkFlag());
//                reservationSettingSpeDayEntityService.save(reservationSettingSpeDayEntity);
//            }
//            for (ReservationSettingSpeTimeMo reservationSettingSpeTimeMo : reservationSettingMainMo.getReservationSettingSpeTimeMoList()) {
//                ReservationSettingSpeTimeEntity reservationSettingSpeTimeEntity = new ReservationSettingSpeTimeEntity();
//                reservationSettingSpeTimeEntity.setSettingMainId(reservationSettingMainEntity.getId());
//                reservationSettingSpeTimeEntity.setSpeDay(reservationSettingSpeTimeMo.getSpeDay());
//                reservationSettingSpeTimeEntity.setSpeStartRange(reservationSettingSpeTimeMo.getSpeStartRange());
//                reservationSettingSpeTimeEntity.setSpeEndRange(reservationSettingSpeTimeMo.getSpeEndRange());
//                reservationSettingSpeTimeEntity.setSpeAllNum(reservationSettingSpeTimeMo.getSpeAllNum());
//                reservationSettingSpeTimeEntityService.save(reservationSettingSpeTimeEntity);
//            }
            BeanUtil.copyProperties(reservationSettingMainEntity, vo);

        } else {

        }
        return vo;
    }

    @Override
    public Boolean saveSpeDayAndTime(ReservationSettingSpeDayMo reservationSettingSpeDayMo) {
        // 保存特殊设置记录




        // 将特殊记录覆盖到record表上




        return null;
    }

    void createDetailSetting(Long id, List<ReservationSettingWorkDayMo> reservationSettingWorkDayMoList, List<ReservationSettingWorkTimeMo> reservationSettingWorkTimeMoList) {
        reservationSettingWorkDayMoList.sort(new Comparator<ReservationSettingWorkDayMo>() {
            @Override
            public int compare(ReservationSettingWorkDayMo o1, ReservationSettingWorkDayMo o2) {
                return o1.getDayNo() - o2.getDayNo();
            }
        });
        int allNum = 0;
        for (ReservationSettingWorkTimeMo reservationSettingWorkTimeMo : reservationSettingWorkTimeMoList) {
            allNum = allNum + reservationSettingWorkTimeMo.getAllNum();
        }
        Date today = DateUtil.date();
        for (int i = 0; i < 365; i++) {
            // 今天往后 i 天
            Date date = DateUtil.offsetDay(today, i);
            // 今天往后 i 天是星期几
            Week week = DateUtil.dayOfWeekEnum(date);
            ReservationSettingDayRecordEntity reservationSettingDayRecordEntity = new ReservationSettingDayRecordEntity();
            reservationSettingDayRecordEntity.setSettingMainId(id);
            reservationSettingDayRecordEntity.setDayName(DateUtil.formatDate(date));
            reservationSettingDayRecordEntity.setDayStatus(reservationSettingWorkDayMoList.get(week.getValue() - 1).getWorkFlag());
            reservationSettingDayRecordEntity.setAllNum(allNum);
            reservationSettingDayRecordEntity.setHaveNum(allNum);
            reservationSettingDayRecordEntity.setUsedNum(0);
            reservationSettingDayRecordEntityService.save(reservationSettingDayRecordEntity);
            for (ReservationSettingWorkTimeMo reservationSettingWorkTimeMo : reservationSettingWorkTimeMoList) {
                ReservationSettingTimeRecordEntity reservationSettingTimeRecordEntity = new ReservationSettingTimeRecordEntity();
                reservationSettingTimeRecordEntity.setDayRecordId(reservationSettingDayRecordEntity.getId());
                reservationSettingTimeRecordEntity.setTimeRange(reservationSettingWorkTimeMo.getStartRange() + "-" + reservationSettingWorkTimeMo.getEndRange());
                reservationSettingTimeRecordEntity.setAllNum(reservationSettingWorkTimeMo.getAllNum());
                reservationSettingTimeRecordEntity.setHaveNum(reservationSettingWorkTimeMo.getAllNum());
                reservationSettingTimeRecordEntity.setUsedNum(0);
                reservationSettingTimeRecordEntityService.save(reservationSettingTimeRecordEntity);
            }
        }
    }

    void updateDetailSetting() {

    }


    @Override
    public Boolean openReservation(ReservationSettingMainControllerMo reservationSettingMainMo) {
        if (ObjectUtil.isNull(reservationSettingMainMo) || 0 == reservationSettingMainMo.getId()) {
            throw new Exception200("");
        }
        ReservationSettingMainEntity reservationSettingMainEntity = reservationSettingMainEntityService.getById(reservationSettingMainMo.getId());
        if (ObjectUtil.isNull(reservationSettingMainEntity)) {
            throw new Exception200("");
        }
        reservationSettingMainEntity.setStatus("开启");
        reservationSettingMainEntityService.save(reservationSettingMainEntity);
        return true;
    }

    @Override
    public ReservationApplyMainControllerVo getDefaultReservationApplyWorkDaySetting() {
        return getReservationApplyWorkDaySetting("default");
    }

    @Override
    public ReservationApplyMainControllerVo getReservationApplyWorkDaySetting(String code) {
        ReservationApplyMainControllerVo vo = new ReservationApplyMainControllerVo();
        // 查看code对应的配置主表记录
        QueryWrapper<ReservationSettingMainEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ReservationSettingMainEntity::getCode, code);
        ReservationSettingMainEntity reservationSettingMainEntity = reservationSettingMainEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(reservationSettingMainEntity)) {
            throw new Exception200("code:" + code + "没有对应的配置主表记录");
        }
        vo.setCode(code);
        vo.setOpenDays(reservationSettingMainEntity.getOpenDays());
        vo.setOpenAddr(reservationSettingMainEntity.getOpenAddr());
        List<ReservationDayBo> list = new ArrayList<>();
        // 今天
        Date today = DateUtil.date();
        for (int i = 0; i < reservationSettingMainEntity.getOpenDays(); i++) {
            // 今天往后 i 天
            Date date = DateUtil.offsetDay(today, i);
            String formatDate = DateUtil.formatDate(date);
            ReservationDayBo reservationDayBo = getRedisDay(code, formatDate);
            list.add(reservationDayBo);
        }
        vo.setList(list);
        return vo;
    }

    @Override
    public ReservationApplyWorkDayControllerVo getDefaultReservationApplyWorkTimeSetting(String day) {
        return getReservationApplyWorkTimeSetting("default", day);
    }

    @Override
    public ReservationApplyWorkDayControllerVo getReservationApplyWorkTimeSetting(String code, String day) {
        ReservationApplyWorkDayControllerVo vo = new ReservationApplyWorkDayControllerVo();
        vo.setDayName(day);
        ReservationDayBo reservationDayBo = getRedisDay(code, day);
        vo.setReservationTimeBoList(reservationDayBo.getReservationTimeBoList());
        return vo;
    }


    private int getDayHaveNum(String code, String day) {
        // day剩余预约数
        String str = stringRedisTemplate.opsForValue().get("reservation" + code + day + "have" + "num");
        if (StrUtil.isBlank(str)) {
            ReservationDayBo bo = getRedisDay(code, day);
            stringRedisTemplate.opsForValue().set("reservation" + code + day + "have" + "num", "" + bo.getHaveNum());
            return bo.getHaveNum();
        } else {
            return Integer.parseInt(str);
        }
    }

    private int getDayTimeHaveNum(String code, String day, String startRange, String endRange) {
        // time剩余预约数
        String str = stringRedisTemplate.opsForValue().get("reservation" + code + day + startRange + endRange + "have" + "num");
        if (StrUtil.isBlank(str)) {
            ReservationDayBo bo = getRedisDay(code, day);
            for (ReservationTimeBo reservationTimeBo : bo.getReservationTimeBoList()) {
                if (StrUtil.equals(startRange, reservationTimeBo.getStartRange()) && StrUtil.equals(endRange, reservationTimeBo.getEndRange())) {
                    stringRedisTemplate.opsForValue().set("reservation" + code + day + startRange + endRange + "have" + "num", "" + reservationTimeBo.getHaveNum());
                    return reservationTimeBo.getHaveNum();
                }
            }
        } else {
            return Integer.parseInt(str);
        }
        return 0;
    }

    private ReservationDayBo getRedisDay(String code, String day) {
        ReservationDayBo resultBo = new ReservationDayBo();
        resultBo.setCode(code);
        resultBo.setDayName(day);
        // 先去查对象
        String json = stringRedisTemplate.opsForValue().get("reservation" + code + day);
        if (StrUtil.isBlank(json)) {
            resultBo = getDbDay(code, day);
            stringRedisTemplate.opsForValue().set("reservation" + code + day, JSONObject.toJSONString(resultBo));
        } else {
            resultBo = JSONObject.parseObject(json, ReservationDayBo.class);
        }
        return resultBo;
    }

    private ReservationDayBo getDbDay(String code, String day) {
        ReservationDayBo resultBo = new ReservationDayBo();
        resultBo.setCode(code);
        resultBo.setDayName(day);
        // 查配置主表
        QueryWrapper<ReservationSettingMainEntity> reservationSettingMainEntityQueryWrapper = new QueryWrapper<>();
        reservationSettingMainEntityQueryWrapper.lambda().eq(ReservationSettingMainEntity::getCode, code);
        ReservationSettingMainEntity reservationSettingMainEntity = reservationSettingMainEntityService.getOne(reservationSettingMainEntityQueryWrapper);
        // 查看day是不是工作日
        String workFlag = "";
        QueryWrapper<ReservationSettingSpeDayEntity> reservationSettingSpeWorkEntityQueryWrapper = new QueryWrapper<>();
        reservationSettingSpeWorkEntityQueryWrapper.lambda()
                .eq(ReservationSettingSpeDayEntity::getSettingMainId, reservationSettingMainEntity.getId())
                .eq(ReservationSettingSpeDayEntity::getSpeDay, day);
        ReservationSettingSpeDayEntity reservationSettingSpeDayEntity = reservationSettingSpeDayEntityService.getOne(reservationSettingSpeWorkEntityQueryWrapper);
        if (ObjectUtil.isNull(reservationSettingSpeDayEntity)) {
            // 今天往后 i 天是星期几
            Week week = DateUtil.dayOfWeekEnum(DateUtil.parse(day));
            // 查看code对应的配置星期工作日表记录
            QueryWrapper<ReservationSettingWorkDayEntity> reservationSettingWorkDayEntityQueryWrapper = new QueryWrapper<>();
            reservationSettingWorkDayEntityQueryWrapper.lambda()
                    .eq(ReservationSettingWorkDayEntity::getSettingMainId, reservationSettingMainEntity.getId())
                    .eq(ReservationSettingWorkDayEntity::getDayNo, week.getValue());
            ReservationSettingWorkDayEntity reservationSettingWorkDayEntity = reservationSettingWorkDayEntityService.getOne(reservationSettingWorkDayEntityQueryWrapper);
            workFlag = reservationSettingWorkDayEntity.getWorkFlag();
        } else {
            workFlag = reservationSettingSpeDayEntity.getWorkFlag();
        }
        int allNum = 0;
        int usedNum = 0;
        int haveNum = 0;
        resultBo.setDayStatus(workFlag);
        if (StrUtil.equals(workFlag, "工作日")) {
            List<ReservationTimeBo> reservationTimeBoList = new ArrayList<>();
            // 获取到day对应的time表，和设置的allNum
            QueryWrapper<ReservationSettingSpeTimeEntity> reservationSettingSpeTimeEntityQueryWrapper = new QueryWrapper<>();
            reservationSettingSpeTimeEntityQueryWrapper.lambda()
                    .eq(ReservationSettingSpeTimeEntity::getSettingMainId, reservationSettingMainEntity.getId())
                    .eq(ReservationSettingSpeTimeEntity::getSpeDay, day)
                    .orderByAsc(ReservationSettingSpeTimeEntity::getSpeStartRange);
            List<ReservationSettingSpeTimeEntity> reservationSettingSpeTimeEntityList = reservationSettingSpeTimeEntityService.list(reservationSettingSpeTimeEntityQueryWrapper);
            if (ObjectUtil.isEmpty(reservationSettingSpeTimeEntityList)) {
                QueryWrapper<ReservationSettingWorkTimeEntity> reservationSettingWorkTimeEntityQueryWrapper = new QueryWrapper<>();
                reservationSettingWorkTimeEntityQueryWrapper.lambda()
                        .eq(ReservationSettingWorkTimeEntity::getSettingMainId, reservationSettingMainEntity.getId())
                        .orderByAsc(ReservationSettingWorkTimeEntity::getStartRange);
                List<ReservationSettingWorkTimeEntity> reservationSettingWorkTimeEntityList = reservationSettingWorkTimeEntityService.list(reservationSettingWorkTimeEntityQueryWrapper);
                reservationTimeBoList = reservationSettingWorkTimeEntityList.stream().map(reservationSettingWorkTimeEntity -> {
                    ReservationTimeBo reservationTimeBo = new ReservationTimeBo();
                    reservationTimeBo.setDayName(day);
                    reservationTimeBo.setStartRange(reservationSettingWorkTimeEntity.getStartRange());
                    reservationTimeBo.setEndRange(reservationSettingWorkTimeEntity.getEndRange());
                    reservationTimeBo.setAllNum(reservationSettingWorkTimeEntity.getAllNum());
                    return reservationTimeBo;
                }).collect(Collectors.toList());
            } else {
                reservationTimeBoList = reservationSettingSpeTimeEntityList.stream().map(reservationSettingSpeTimeEntity -> {
                    ReservationTimeBo reservationTimeBo = new ReservationTimeBo();
                    reservationTimeBo.setDayName(day);
                    reservationTimeBo.setStartRange(reservationSettingSpeTimeEntity.getSpeStartRange());
                    reservationTimeBo.setEndRange(reservationSettingSpeTimeEntity.getSpeEndRange());
                    reservationTimeBo.setAllNum(reservationSettingSpeTimeEntity.getSpeAllNum());
                    return reservationTimeBo;
                }).collect(Collectors.toList());
            }
            // 获取到time表对应的usedNum
            QueryWrapper<ReservationApplyRecordEntity> reservationApplyRecordEntityQueryWrapper = new QueryWrapper<>();
            for (ReservationTimeBo reservationTimeBo : reservationTimeBoList) {
                allNum = allNum + reservationTimeBo.getAllNum();
                reservationApplyRecordEntityQueryWrapper = new QueryWrapper<>();
                reservationApplyRecordEntityQueryWrapper.lambda()
                        .eq(ReservationApplyRecordEntity::getSettingMainId, reservationSettingMainEntity.getId())
                        .eq(ReservationApplyRecordEntity::getDayName, day)
                        .ge(ReservationApplyRecordEntity::getStartRange, reservationTimeBo.getStartRange())
                        .le(ReservationApplyRecordEntity::getEndRange, reservationTimeBo.getEndRange());
                int count = reservationApplyRecordEntityService.count(reservationApplyRecordEntityQueryWrapper);
                reservationTimeBo.setUsedNum(count);
                reservationTimeBo.setHaveNum(reservationTimeBo.getAllNum() - count);
                usedNum = usedNum + count;
                haveNum = haveNum + reservationTimeBo.getHaveNum();
            }
            resultBo.setAllNum(allNum);
            resultBo.setUsedNum(usedNum);
            resultBo.setHaveNum(haveNum);
            resultBo.setReservationTimeBoList(reservationTimeBoList);
        } else {
            resultBo.setAllNum(allNum);
            resultBo.setUsedNum(usedNum);
            resultBo.setHaveNum(haveNum);
            resultBo.setReservationTimeBoList(new ArrayList<>());
        }
        return resultBo;
    }

}
