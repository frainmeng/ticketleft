/**
 * 
 */
package com.frain.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kalven.meng
 *
 */
public class TaskQueue {
	private Map<String, QueryTask> taskMap = new HashMap<String, QueryTask>();
	
	public boolean isExists(String taskName){
		return taskMap.containsKey(taskName);
	}
	
	public void add (QueryTask queryTask) {
		taskMap.put(queryTask.getTaskName(), queryTask);
	}
	
	public void remove (String taskName){
		taskMap.remove(taskName);
	}
	
	public QueryTask get(String taskName) {
		return taskMap.get(taskName);
	}
	
	public List<QueryTask> getTaskList() {
		List<QueryTask> list = new ArrayList<QueryTask>(taskMap.values());
		return list;
	}
	
	
}
