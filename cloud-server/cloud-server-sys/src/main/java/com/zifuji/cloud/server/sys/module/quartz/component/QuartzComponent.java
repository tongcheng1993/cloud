package com.zifuji.cloud.server.sys.module.quartz.component;

import com.zifuji.cloud.server.sys.module.quartz.bo.QuartzRecordComponentMo;
import lombok.AllArgsConstructor;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class QuartzComponent {


    private Scheduler scheduler;

    private QuartzJobBean getInstanceClass(String classname) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(classname);
        return (QuartzJobBean) clazz.newInstance();

    }

    /**
     * 添加并启动定时任务
     *
     * @param jobGroupName
     * @param jobClassName
     * @param cronExpression
     * @return
     * @throws SchedulerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public Boolean  addAndStartQuartzJob(String jobGroupName, String jobClassName, String cronExpression) throws SchedulerException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        // 启动调度器
        scheduler.start();
        // 构建Job信息
        JobDetail jobDetail = JobBuilder.newJob(getInstanceClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();
        // Cron表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(cronExpression);
        //根据Cron表达式构建一个Trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName).withSchedule(cron).build();
        // 调取器增加任务
        scheduler.scheduleJob(jobDetail, trigger);
        return true;
    }

    /**
     * 重新配置定时任务
     * @param jobGroupName
     * @param jobClassName
     * @param cronExpression
     * @return
     * @throws Exception
     */
    public Boolean cronJob(String jobGroupName, String jobClassName, String cronExpression) throws Exception {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
        // 表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        // 根据Cron表达式构建一个Trigger
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        // 按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);
        return true;
    }

    /**
     * 删除定时任务
     *
     * @param jobGroupName
     * @param jobClassName
     * @return
     * @throws SchedulerException
     */
    public Boolean removeQuartzJob(String jobGroupName, String jobClassName) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
        return true;
    }

    /**
     * 暂停定时任务
     *
     * @param jobGroupName
     * @param jobClassName
     * @return
     * @throws SchedulerException
     */
    public Boolean pauseJob(String jobGroupName, String jobClassName) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
        return true;
    }

    /**
     * 恢复定时任务
     *
     * @param jobGroupName
     * @param jobClassName
     * @return
     * @throws SchedulerException
     */
    public Boolean resumeJob(String jobGroupName, String jobClassName) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
        return true;
    }


    public List<QuartzRecordComponentMo> getAllJob(){
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = null;
        List<QuartzRecordComponentMo> jobList = new ArrayList();
        try {
            jobKeys = scheduler.getJobKeys(matcher);
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    QuartzRecordComponentMo job = new QuartzRecordComponentMo();
                    job.setName(jobKey.getName());
                    job.setJobGroupName(jobKey.getGroup());
                    job.setDescription("触发器:" + trigger.getKey());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    job.setStatus(triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        job.setCronExpression(cronExpression);
                    }
                    jobList.add(job);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;
    }


}
