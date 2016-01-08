/**
 * 
 */
package com.frain.service.schedule;

import java.util.List;

import com.frain.model.QueryTask;
import com.frain.model.TaskQueue;
import com.frain.util.DateUtils;

/**
 * @author kalven.meng
 *
 */
public class TaskService {
	private static TaskService self = new TaskService(); 
	private TaskQueue tasks = ScheduleService.getInstance().getTasks();
	
	private TaskService() {};
	public static TaskService getInstance() {
		return self ;
	}
	
	public List<QueryTask> queryTaskList () {
		return tasks.getTaskList();
	}
	
	public void addTask (QueryTask queryTask) {
		tasks.add(queryTask);
	}
	
	public void delTask (String taskName) {
		tasks.remove(taskName);
	}
	
	public void cancelTask (String taskName) {
		QueryTask queryTask = tasks.get(taskName);
		if (null != queryTask) {
			queryTask.setStatus(false);
			queryTask.setUpdateDate(DateUtils.getCurrentDateStr());
		}
	}
	public void activeTask (String taskName) {
		QueryTask queryTask = tasks.get(taskName);
		if (null != queryTask) {
			queryTask.setStatus(true);
			queryTask.setUpdateDate(DateUtils.getCurrentDateStr());
		}
	}
	
	
}
