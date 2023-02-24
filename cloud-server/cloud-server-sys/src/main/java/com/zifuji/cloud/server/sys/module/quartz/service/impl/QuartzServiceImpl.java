package com.zifuji.cloud.server.sys.module.quartz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.bean.BaseConstant;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.sys.db.quartz.entity.QuartzRecordEntity;
import com.zifuji.cloud.server.sys.db.quartz.service.QuartzRecordEntityService;
import com.zifuji.cloud.server.sys.module.quartz.bo.QuartzRecordBo;
import com.zifuji.cloud.server.sys.module.quartz.component.QuartzComponent;
import com.zifuji.cloud.server.sys.module.quartz.mapper.QuartzMapper;
import com.zifuji.cloud.server.sys.module.quartz.mo.QuartzRecordMo;

import com.zifuji.cloud.server.sys.module.quartz.qo.QuartzRecordPageQo;
import com.zifuji.cloud.server.sys.module.quartz.service.QuartzService;
import com.zifuji.cloud.server.sys.module.quartz.vo.QuartzRecordVo;
import com.zifuji.cloud.starter.web.util.MyBatisPlusUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class QuartzServiceImpl implements QuartzService {

    private QuartzRecordEntityService quartzRecordEntityService;

    private QuartzMapper quartzMapper;

    private QuartzComponent quartzComponent;

    @Override
    public QuartzRecordVo saveQuartzRecord(QuartzRecordMo quartzRecordMo) throws Exception {
        QuartzRecordVo quartzRecordVo = new QuartzRecordVo();
        if (ObjectUtil.isNull(quartzRecordMo.getId())) {
            QuartzRecordEntity quartzRecordEntity = new QuartzRecordEntity();
            BeanUtil.copyProperties(quartzRecordMo, quartzRecordEntity);
            quartzComponent.addAndStartQuartzJob(quartzRecordMo.getJobGroupName(), quartzRecordMo.getJobClassName(), quartzRecordMo.getCronExpression());
            quartzRecordEntityService.save(quartzRecordEntity);
             if (StrUtil.equals(quartzRecordEntity.getStatus(), BaseConstant.CODE_START_STOP_TYPE_2)) {
                quartzComponent.pauseJob(quartzRecordEntity.getJobGroupName(), quartzRecordEntity.getJobClassName());
            }
            BeanUtil.copyProperties(quartzRecordEntity, quartzRecordVo);
        } else {
            QuartzRecordEntity quartzRecordEntity = quartzRecordEntityService.getById(quartzRecordMo.getId());
            if (!StrUtil.equals(quartzRecordEntity.getJobGroupName(), quartzRecordMo.getJobGroupName())) {
                throw new Exception200("");
            }
            if (!StrUtil.equals(quartzRecordEntity.getJobClassName(), quartzRecordMo.getJobClassName())) {
                throw new Exception200("类名称不能修改");
            }
            if (!StrUtil.equals(quartzRecordEntity.getCronExpression(), quartzRecordMo.getCronExpression())) {
                quartzComponent.cronJob(quartzRecordEntity.getJobGroupName(), quartzRecordEntity.getJobClassName(), quartzRecordMo.getCronExpression());
            }
            if (StrUtil.equals(BaseConstant.CODE_START_STOP_TYPE_1, quartzRecordMo.getStatus())) {
                quartzComponent.resumeJob(quartzRecordEntity.getJobGroupName(), quartzRecordEntity.getJobClassName());
            } else if (StrUtil.equals(BaseConstant.CODE_START_STOP_TYPE_2, quartzRecordMo.getStatus())) {
                quartzComponent.pauseJob(quartzRecordEntity.getJobGroupName(), quartzRecordEntity.getJobClassName());
            } else {
                throw new Exception200("定时任务状态编码错误");
            }
            BeanUtil.copyProperties(quartzRecordMo, quartzRecordEntity);
            quartzRecordEntityService.updateById(quartzRecordEntity);
            BeanUtil.copyProperties(quartzRecordEntity, quartzRecordVo);
        }
        return quartzRecordVo;
    }

    @Override
    public Boolean delQuartzRecord(Long id) throws Exception {
        QuartzRecordEntity quartzRecordEntity = quartzRecordEntityService.getById(id);
        quartzComponent.removeQuartzJob(quartzRecordEntity.getJobGroupName(), quartzRecordEntity.getJobClassName());
        return quartzRecordEntityService.removeById(id);
    }

    @Override
    public IPage<QuartzRecordVo> queryPageQuartzRecord(QuartzRecordPageQo quartzRecordPageQo) {
        IPage<QuartzRecordBo> page = selectPageQuartzRecord(quartzRecordPageQo);
        return page.convert(bo -> {
            QuartzRecordVo vo = new QuartzRecordVo();
            BeanUtil.copyProperties(bo, vo);
            return vo;
        });
    }

    @Override
    public Boolean syncQuartzList() {


        List<QuartzRecordBo> list = quartzComponent.getAllJob();

        for (QuartzRecordBo bo : list) {
            log.info("job：{}", bo);
        }


        return true;
    }

    private IPage<QuartzRecordBo> selectPageQuartzRecord(QuartzRecordPageQo quartzRecordPageQo) {
        Page<QuartzRecordBo> page = new Page<QuartzRecordBo>(quartzRecordPageQo.getCurrent(), quartzRecordPageQo.getSize());
        QueryWrapper<QuartzRecordBo> queryWrapper = new QueryWrapper<QuartzRecordBo>();
        if (StrUtil.isNotBlank(quartzRecordPageQo.getName())) {
            queryWrapper.eq("name", quartzRecordPageQo.getName());
        } else {

        }
        MyBatisPlusUtil.orderWrapper(queryWrapper, quartzRecordPageQo.getOrders());
        return quartzMapper.selectPageQuartzRecord(page, queryWrapper);
    }


}