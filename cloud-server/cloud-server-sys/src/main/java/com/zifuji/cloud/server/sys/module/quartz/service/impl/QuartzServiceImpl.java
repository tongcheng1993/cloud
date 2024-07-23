package com.zifuji.cloud.server.sys.module.quartz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.sys.module.quartz.controller.qo.QueryJobDetailQo;
import com.zifuji.cloud.server.sys.module.quartz.controller.vo.JobDetailVo;
import com.zifuji.cloud.server.sys.module.quartz.service.QuartzService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class QuartzServiceImpl implements QuartzService {

	private Scheduler scheduler;

	private QuartzJobBean getInstanceClass(String classname)
			throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		Class<?> clazz = Class.forName(classname);
		return (QuartzJobBean) clazz.newInstance();

	}

	@Override
	public IPage<JobDetailVo> queryPageJobDetail(QueryJobDetailQo queryJobDetailQo) {
		List<JobDetailVo> jobDetailVoList = new ArrayList<>();
		try {
			List<String> jobGroupNameList = scheduler.getJobGroupNames();
			for (String jobGroupName : jobGroupNameList) {
				Set<JobKey> jobKeySet = scheduler.getJobKeys(GroupMatcher.groupEquals(jobGroupName));
				for (JobKey jobKey : jobKeySet) {
					JobDetail jobDetail = scheduler.getJobDetail(jobKey);
					JobDetailVo vo = new JobDetailVo();
					vo.setJobGroupName(jobGroupName);
					vo.setJobKey(jobKey.getName());
					vo.setJobDescription(jobDetail.getDescription());
					jobDetailVoList.add(vo);
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		if (queryJobDetailQo.getCurrent() < 1) {
			queryJobDetailQo.setCurrent(1L);
		}
		int size = (int) queryJobDetailQo.getSize();
		int current = (int) (queryJobDetailQo.getCurrent());
		int start = (current - 1) * size;
		int tmp = start + size;
		int end = Math.min(tmp, jobDetailVoList.size());
		
		
		Page<JobDetailVo> page = new Page<>(queryJobDetailQo.getCurrent(),queryJobDetailQo.getSize());
		
		page.setTotal(jobDetailVoList.size());
		if (end > start) {
			page.setRecords(jobDetailVoList.subList(start, end));
		} else {
			page.setRecords(new ArrayList<>());
		}
		return page;
	}
}
