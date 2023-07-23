package com.zifuji.cloud.server.sys.module.seq.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.sys.module.seq.bo.SeqComponentMo;
import com.zifuji.cloud.server.sys.module.seq.mapper.SeqMapper;
import com.zifuji.cloud.server.sys.module.seq.mo.SaveSeqControllerMo;
import com.zifuji.cloud.server.base.util.MyBatisPlusUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.constant.BaseConstant;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.sys.db.seq.entity.SeqEntity;
import com.zifuji.cloud.server.sys.db.seq.service.SeqEntityService;
import com.zifuji.cloud.server.sys.module.seq.qo.SeqPageQo;
import com.zifuji.cloud.server.sys.module.seq.service.SeqService;
import com.zifuji.cloud.server.sys.module.seq.vo.SeqControllerVo;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SeqServiceImpl implements SeqService {

    private StringRedisTemplate stringRedisTemplate;

    private SeqEntityService seqEntityService;

    private SeqMapper seqMapper;


    @Override
    public List<SeqControllerVo> queryListSeq(SeqPageQo seqPageQo) {
        List<SeqComponentMo> list = selectListSeq(seqPageQo);
        return list.stream().map(seqBo -> {
            SeqControllerVo seqVo = new SeqControllerVo();
            BeanUtil.copyProperties(seqBo, seqVo);
            return seqVo;
        }).collect(Collectors.toList());
    }


    @Override
    public IPage<SeqControllerVo> queryPageSeq(SeqPageQo seqPageQo) {
        IPage<SeqComponentMo> page = selectPageSeq(seqPageQo);
        return page.convert(seqBo -> {
            SeqControllerVo seqVo = new SeqControllerVo();
            BeanUtil.copyProperties(seqBo, seqVo);
            return seqVo;
        });
    }

    @Override
    public SeqControllerVo getSeqById(Long id) {
        SeqControllerVo vo = new SeqControllerVo();
        SeqEntity seqEntity = seqEntityService.getById(id);
        BeanUtil.copyProperties(seqEntity,vo);
        return vo;
    }

    @Override
    public SeqControllerVo saveSeq(SaveSeqControllerMo saveSeqMo) {
        return null;
    }

    private List<SeqComponentMo> selectListSeq(SeqPageQo seqPageQo) {
        QueryWrapper<SeqComponentMo> queryWrapper = new QueryWrapper<SeqComponentMo>();
        if (StrUtil.isNotBlank(seqPageQo.getName())) {
            queryWrapper.eq("zss.name", seqPageQo.getName());
        } else {

        }
        MyBatisPlusUtil.orderWrapper(queryWrapper, seqPageQo.getOrders());
        return seqMapper.selectListSeq(queryWrapper);
    }

    private IPage<SeqComponentMo> selectPageSeq(SeqPageQo seqPageQo) {
        Page<SeqComponentMo> page = new Page<SeqComponentMo>(seqPageQo.getCurrent(), seqPageQo.getSize());
        QueryWrapper<SeqComponentMo> queryWrapper = new QueryWrapper<SeqComponentMo>();
        if (StrUtil.isNotBlank(seqPageQo.getName())) {
            queryWrapper.eq("zss.name", seqPageQo.getName());
        } else {

        }
        MyBatisPlusUtil.orderWrapper(queryWrapper, seqPageQo.getOrders());
        return seqMapper.selectPageSeq(page, queryWrapper);
    }


    @Override
    public String getNextSeq(String code) {
        QueryWrapper<SeqEntity> queryWrapper = new QueryWrapper<SeqEntity>();
        queryWrapper.lambda().eq(SeqEntity::getCode, code);
        SeqEntity seqEntity = seqEntityService.getOne(queryWrapper);
        if (ObjectUtil.isNull(seqEntity)) {
            throw new Exception200("");
        }
        // 获取流水号num
        String timeCode = "";
        String timeFormat = "";
        SimpleDateFormat sdf = new SimpleDateFormat();
        switch (seqEntity.getRebornType()) {
            case BaseConstant.CODE_REBORN_TYPE_1:
                timeFormat = "yyyyMMdd";
                sdf.applyPattern(timeFormat);
                timeCode = sdf.format(new Date());
                break;
            case BaseConstant.CODE_REBORN_TYPE_2:
                timeFormat = "yyyyMM";
                sdf.applyPattern(timeFormat);
                timeCode = sdf.format(new Date());
                break;
            case BaseConstant.CODE_REBORN_TYPE_3:
                timeFormat = "yyyy";
                sdf.applyPattern(timeFormat);
                timeCode = sdf.format(new Date());
                break;
            default:
                break;
        }
        String key = BaseConstant.REDIS_SEQ + timeCode + code;
        Long redisNum = stringRedisTemplate.opsForValue().increment(key);
        if (redisNum == 1L) {
            switch (seqEntity.getRebornType()) {
                case BaseConstant.CODE_REBORN_TYPE_1:
                    stringRedisTemplate.expire(key, 1, TimeUnit.DAYS);
                    break;
                case BaseConstant.CODE_REBORN_TYPE_2:
                    stringRedisTemplate.expire(key, 31, TimeUnit.DAYS);
                    break;
                case BaseConstant.CODE_REBORN_TYPE_3:
                    stringRedisTemplate.expire(key, 366, TimeUnit.DAYS);
                    break;
                default:
                    break;
            }
        }

        // 将num补全位数
        String Longformat = "";
        int size = String.valueOf(redisNum).length();
        if (size < seqEntity.getSupplementNum()) {
            String format = "%" + seqEntity.getSupplementFlag() + seqEntity.getSupplementNum() + "d";
            Longformat = String.format(format, redisNum);
        }
        return seqEntity.getPrefix() + timeCode + Longformat + seqEntity.getSuffix();
    }


}
