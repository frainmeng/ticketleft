/**
 * 
 */
package com.frain.web.controller.imp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frain.model.RespModel;
import com.frain.service.schedule.ScheduleService;
import com.frain.web.controller.Controller;

/**
 * @author kalven.meng
 *
 */
public class ServiceController implements Controller {
	private static Logger log = LoggerFactory.getLogger(ServiceController.class);
	public static final String START = "/service/start";
	public static final String STOP = "/service/stop";
	public static final String RESTART = "/service/restart";
	public static final String STATUS = "/service/status";
	public static final String[] URIS = {START,STOP,RESTART,STATUS};
	private ScheduleService scheduleService = ScheduleService.getInstance();
	@Override 
	public RespModel service(String path, HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		RespModel respModel = new RespModel();
		respModel.setRespStatus(RespModel.ERROR);
		try {
			if (START.equalsIgnoreCase(path)) {
				start(respModel);
			} else if (STOP.equalsIgnoreCase(path)) {
				stop(respModel);
			} else if (STATUS.equalsIgnoreCase(path)) {
				status(respModel);
			}  else if (RESTART.equalsIgnoreCase(path)) {
				restart(respModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			respModel.setData(e.toString());
			log.error("²Ù×÷Ê§°Ü:" + path, e);
		} 
		return respModel;
	}
	
	private void start(RespModel respModel) {
		if (scheduleService.start()){
			respModel.setRespStatus(RespModel.SUCCESS);
		} else {
			respModel.setRespStatus(RespModel.ERROR);
		}
	}
	
	private void stop(RespModel respModel) {
		if (scheduleService.stop()){
			respModel.setRespStatus(RespModel.SUCCESS);
		} else {
			respModel.setRespStatus(RespModel.ERROR);
		}
	} 
	
	private void status(RespModel respModel) {
		if (scheduleService.status()){
			respModel.setRespStatus(RespModel.SUCCESS);
			respModel.setData("Service is running now!!!");
		} else {
			respModel.setRespStatus(RespModel.SUCCESS);
			respModel.setData("Service is stoped now!!!");
		}
	}
	
	private void restart(RespModel respModel) {
		if (scheduleService.restart()){
			respModel.setRespStatus(RespModel.SUCCESS);
		} else {
			respModel.setRespStatus(RespModel.ERROR);
		}
	}
}
