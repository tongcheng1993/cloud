package com.zifuji.cloud.server.sys.module.quartz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.bean.BaseConstant;
import com.zifuji.cloud.base.exception.Exception20000;
import com.zifuji.cloud.server.sys.db.quartz.entity.QuartzRecordEntity;
import com.zifuji.cloud.server.sys.db.quartz.service.QuartzRecordEntityService;
import com.zifuji.cloud.server.sys.module.quartz.controller.bo.QuartzRecordComponentMo;
import com.zifuji.cloud.server.sys.module.quartz.component.QuartzComponent;
import com.zifuji.cloud.server.sys.module.quartz.controller.mo.ResetQuartzRecordMo;
import com.zifuji.cloud.server.sys.module.quartz.mapper.QuartzMapper;
import com.zifuji.cloud.server.sys.module.quartz.controller.mo.AddQuartzRecordMo;

import com.zifuji.cloud.server.sys.module.quartz.controller.qo.QuartzRecordQo;
import com.zifuji.cloud.server.sys.module.quartz.service.QuartzService;
import com.zifuji.cloud.server.sys.module.quartz.controller.vo.QuartzRecordVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.quartz.SchedulerException;
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
    public QuartzRecordVo addQuartzRecord(AddQuartzRecordMo quartzRecordMo) {
        QuartzRecordVo quartzRecordVo = new QuartzRecordVo();
        QuartzRecordEntity quartzRecordEntity = new QuartzRecordEntity();
        BeanUtil.copyProperties(quartzRecordMo, quartzRecordEntity);

        boolean flag = false;
        try {
            flag = quartzComponent.addAndStartQuartzJob(quartzRecordMo.getJobGroupName(), quartzRecordMo.getJobClassName(), quartzRecordMo.getCronExpression());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        quartzRecordEntity.setStatus(flag);
        quartzRecordEntityService.save(quartzRecordEntity);
        BeanUtil.copyProperties(quartzRecordEntity, quartzRecordVo);
        return quartzRecordVo;
    }

    @Override
    public QuartzRecordVo resetQuartzRecord(ResetQuartzRecordMo quartzRecordMo) {
        QuartzRecordVo quartzRecordVo = new QuartzRecordVo();
        QuartzRecordEntity quartzRecordEntity = quartzRecordEntityService.getById(quartzRecordMo.getId());
        if (!StrUtil.equals(quartzRecordEntity.getJobGroupName(), quartzRecordMo.getJobGroupName())) {
            throw new Exception20000("组名称不能修改");
        }
        if (!StrUtil.equals(quartzRecordEntity.getJobClassName(), quartzRecordMo.getJobClassName())) {
            throw new Exception20000("类名称不能修改");
        }
        // 修改了cron 表达式
        if (!StrUtil.equals(quartzRecordEntity.getCronExpression(), quartzRecordMo.getCronExpression())) {
            try {
                quartzComponent.cronJob(quartzRecordEntity.getJobGroupName(), quartzRecordEntity.getJobClassName(), quartzRecordMo.getCronExpression());
            } catch (Exception e) {
                throw new Exception20000("修改cron表达式出错");
            }
        }
        try {
            if (quartzRecordMo.getStatus()) {
                quartzComponent.resumeJob(quartzRecordEntity.getJobGroupName(), quartzRecordEntity.getJobClassName());
            } else {
                quartzComponent.pauseJob(quartzRecordEntity.getJobGroupName(), quartzRecordEntity.getJobClassName());
            }
        } catch (SchedulerException e) {
            throw new Exception20000("恢复/暂停定时任务出错");
        }
        BeanUtil.copyProperties(quartzRecordMo, quartzRecordEntity);
        quartzRecordEntityService.updateById(quartzRecordEntity);
        BeanUtil.copyProperties(quartzRecordEntity, quartzRecordVo);
        return quartzRecordVo;
    }

    @Override
    public Boolean delQuartzRecord(Long id)  {
        QuartzRecordEntity quartzRecordEntity = quartzRecordEntityService.getById(id);
        try {
            quartzComponent.removeQuartzJob(quartzRecordEntity.getJobGroupName(), quartzRecordEntity.getJobClassName());
        } catch (SchedulerException e) {
            throw new Exception20000("删除定时任务出错");
        }
        return quartzRecordEntityService.removeById(id);
    }

    @Override
    public IPage<QuartzRecordVo> queryPageQuartzRecord(QuartzRecordQo quartzRecordQo) {
        Page<QuartzRecordEntity> page = new Page<>(quartzRecordQo.getCurrent(), quartzRecordQo.getSize());
        QueryWrapper<QuartzRecordEntity> quartzRecordEntityQueryWrapper = new QueryWrapper<>();




        page = quartzRecordEntityService.page(page, quartzRecordEntityQueryWrapper);
        return page.convert(bo -> {
            QuartzRecordVo vo = new QuartzRecordVo();
            BeanUtil.copyProperties(bo, vo);
            return vo;
        });
    }

    @Override
    public Boolean syncQuartzList() {


        List<QuartzRecordComponentMo> list = quartzComponent.getAllJob();

        for (QuartzRecordComponentMo bo : list) {
            log.info("job：{}", bo);
        }


        return true;
    }




}
