/**
 * 
 */
package com.frain.web.controller.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frain.model.QueryTask;
import com.frain.model.RespModel;
import com.frain.service.schedule.ScheduleService;
import com.frain.service.schedule.TaskService;
import com.frain.util.DateUtils;
import com.frain.web.controller.Controller;
import com.frain.web.controller.ControllerFactory;

/**
 * @author kalven.meng
 *
 */
public class TaskController implements Controller {
	private static Logger log = LoggerFactory.getLogger(TaskController.class);
	public static final String QUERY = "/task/query";
	public static final String ADD = "/task/add";
	public static final String CANCEL = "/task/cancel";
	public static final String ACTIVE = "/task/active";
	public static final String DEL = "/task/del";
	public static final String[] URIS = {QUERY,ADD,CANCEL,ACTIVE,DEL};
	private TaskService taskService = TaskService.getInstance();
	/**
	 * /task/start
	 * /task/stop
	 * /task/status
	 */
	@Override 
	public RespModel service(String path, HttpServletRequest req, HttpServletResponse resp) {
		RespModel respModel = new RespModel();
		respModel.setRespStatus(RespModel.ERROR);
		try {
			
			String taskName = req.getParameter("taskName");
			
			if (QUERY.equalsIgnoreCase(path)) {
				query(respModel);
			} else if (ADD.equalsIgnoreCase(path)) {
				add(req, respModel);
			} else if (CANCEL.equalsIgnoreCase(path)) {
				cancel(taskName, respModel);
			} else if (ACTIVE.equalsIgnoreCase(path)) {
				active(taskName, respModel);
			} else if (DEL.equalsIgnoreCase(path)) {
				del(taskName, respModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			respModel.setData(e.toString());
			log.error("²Ù×÷Ê§°Ü:" + path, e);
		}
		return respModel;
	}
	
	private void query(RespModel respModel) {
		List<QueryTask> taskList = taskService.queryTaskList();
		respModel.setRespStatus(RespModel.SUCCESS);
		respModel.setData(taskList);
	}
	
	private void add (HttpServletRequest req, RespModel respModel) {
		QueryTask queryTask = new QueryTask();
		String taskName = req.getParameter("taskName");
		String reqUri = req.getParameter("reqUri");
		String mailSendTo = req.getParameter("mailSendTo");
		String trainCodeStr = req.getParameter("trainCodeSet");
		queryTask.setTaskName(taskName);
		queryTask.setReqUri(reqUri);
		queryTask.setMailSendTo(mailSendTo);
		queryTask.setTrainCodeSet(buildTrainCodeSet(trainCodeStr));
		queryTask.setCreateDate(DateUtils.getCurrentDateStr());
		queryTask.setUpdateDate(DateUtils.getCurrentDateStr());
		taskService.addTask(queryTask);
	}
	
	private void del (String taskName, RespModel respModel) {
		taskService.delTask(taskName);
	}
	
	private void cancel (String taskName, RespModel respModel) {
		taskService.cancelTask(taskName);
	}
	
	private void active (String taskName, RespModel respModel) {
		taskService.activeTask(taskName);
	}
	
	private Set<String> buildTrainCodeSet(String trainCodeStr){
		Set<String> trainCodeSet = new HashSet<String>();
		if (StringUtils.isNotEmpty(trainCodeStr)) {
			String[] trainCodes = trainCodeStr.split(",");
			for (String trainCode:trainCodes) {
				trainCodeSet.add(trainCode);
			}
		}
		return trainCodeSet;
	}
}
