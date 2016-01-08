/**
 * 
 */
package com.frain.service.schedule;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frain.model.QueryTask;
import com.frain.model.TaskQueue;
import com.frain.service.ticket.QueryTicketLeftService;
import com.frain.util.ConfConstants;
import com.frain.util.DateUtils;
import com.frain.util.PropertyUtils;

/**
 * @author kalven.meng
 *
 */
public class ScheduleService {
	private static Logger log = LoggerFactory.getLogger(ScheduleService.class);	
	private boolean status = false;
	private static ScheduleService self = new ScheduleService();
	
	private TaskQueue tasks = new TaskQueue();
	
	private ScheduledExecutorService executorService;
	
	private ExecutorService executorPool;
	
	private QueryTicketLeftService queryTicketLeftService;
	
	private ScheduleService() {
		init();
	}
	public static ScheduleService getInstance(){
		return self;
	}
	
	private synchronized void init () {
		log.info("init SchduleService 。。。");
		try {
			Properties props = PropertyUtils.getProps(ConfConstants.DEFAULT_TASK_CONFIG);
			if (null != props) {
				QueryTask queryTask = new QueryTask();
				queryTask.setTaskName(props.getProperty("task.name"));
				queryTask.setMailSendTo(props.getProperty("task.mailSendTo"));
				queryTask.setReqUri(props.getProperty("task.reqUri"));
				String trainCodeStr = props.getProperty("task.trainCodeSet");
				Set<String> trainCodeSet = new HashSet<String>();
				if (StringUtils.isNotEmpty(trainCodeStr)) {
					String[] trainCodes = trainCodeStr.split(",");
					for (String trainCode:trainCodes) {
						trainCodeSet.add(trainCode);
					}
				}
				queryTask.setTrainCodeSet(trainCodeSet);
				queryTask.setStatus(false);
				queryTask.setCreateDate(DateUtils.getCurrentDateStr());
				queryTask.setUpdateDate(DateUtils.getCurrentDateStr());
				tasks.add(queryTask);
			}
			executorPool = Executors.newFixedThreadPool(2);
			queryTicketLeftService = new QueryTicketLeftService();
			log.info("初始化默认任务成功！");
		} catch (Exception e) {
			log.error("初始化默认任务失败", e);
		}
		log.info("init SchduleService success!!!");
	}
	
	public boolean addTask(QueryTask queryTask) {
		if (tasks.isExists(queryTask.getTaskName())) {
			return false;
		}
		tasks.add(queryTask);
		return true;
	}
	
	public boolean removeTask(String taskName) {
		tasks.remove(taskName);
		return true;
	}
	
	public boolean cancelTask (String taskName) {
		QueryTask queryTask = tasks.get(taskName);
		if (null != queryTask) {
			queryTask.setStatus(false);
		}
		return queryTask.isStatus();
	}
	
	private void execute (final QueryTask queryTask ) {
		executorPool.submit(new Runnable() {
			@Override
			public void run() {
				try {
					queryTicketLeftService.query(queryTask);
					queryTask.setMessage("success");
				} catch (Exception e) {
					// TODO: handle exception
					queryTask.setMessage(e.toString());
					log.error("任务执行失败:" +queryTask.getTaskName() , e);
				}
				
			}
		});
		log.info("任务提交成功:" + queryTask.getTaskName());
	}
	
	
	public boolean start () {
		try {
			if (status) {
				log.info("定时任务已经启动...");
				return status(); 
			}
			executorService = Executors.newSingleThreadScheduledExecutor();
			Runnable scheduleTask = new Runnable() {
				 
				public void run() {
					// TODO Auto-generated method stub
					try {
						List<QueryTask> list = tasks.getTaskList();
						for (QueryTask queryTask : list) {
							if (queryTask.isStatus()) {
								execute(queryTask);
							}
						}
					} catch (Exception e) {
						log.error("定时任务执行失败...", e);
					}
				}
			};
			executorService.scheduleAtFixedRate(scheduleTask, 0, 5, TimeUnit.SECONDS);
			log.info("定时任务启动成功...");
			status = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			status = false;
			log.error("定时任务启动失败...", e);
		}
		return status();
	}
	
	public boolean stop () {
		
		try {
			if (null != executorService) {
				executorService.shutdownNow();
				executorService = null;
			}
			log.info("定时任务关闭成功...");
			status = false;
		} catch (Exception e) {
			log.error("定时任务关闭失败...",e);
		}
		return !status();
	}
	
	public boolean restart() {
		stop ();
		start();
		return status();
	}
	
	public boolean status () {
		return status;
	}
	/**
	 * @return the tasks
	 */
	public TaskQueue getTasks() {
		return tasks;
	}
	
}
