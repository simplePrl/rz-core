package com.rz.core.practice.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzHelper {
	public static void main(String[] args) {
		QuartzHelper quartzHelper = new QuartzHelper();

		try {
			quartzHelper.test();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("QuartzHelper");
	}

	// [second] [minutes] [hour] [day] [moth] [week] [year]
	//@Scheduled(fixedDelay = "")
	private void test() throws SchedulerException {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		
//		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10); 
//		ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(()->{}, 0, 2, TimeUnit.SECONDS);
		
		JobDetail jobDetail = JobBuilder.newJob(TestJob.class).withIdentity("worker1").build();
		JobDetail jobDetailNew = JobBuilder.newJob(TestJob.class).withIdentity("worker2").build();
		
		Trigger trigger = TriggerBuilder.newTrigger().startNow()				
				.withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
				//.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2))
				.build();
		Trigger triggerNew = TriggerBuilder.newTrigger().startNow()				
				.withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
				//.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2))
				.build();
		
//		scheduler.addJob(jobDetail, false);
//		scheduler.addJob(jobDetailNew, false);
		
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.scheduleJob(jobDetailNew, triggerNew);
		scheduler.start();
	}
}
