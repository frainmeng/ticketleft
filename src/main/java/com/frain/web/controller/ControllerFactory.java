/**
 * 
 */
package com.frain.web.controller;

import java.util.HashMap;
import java.util.Map;

import com.frain.web.controller.imp.ServiceController;
import com.frain.web.controller.imp.TaskController;

/**
 * @author kalven.meng
 *
 */
public class ControllerFactory {
	private static ControllerFactory self = new ControllerFactory();
	private Map<String, Controller> controllerMap = new HashMap<String, Controller>();
	private ControllerFactory (){init();}
	
	public static ControllerFactory getInstance() {
		return self;
	}
	
	private void init() {
		Controller serviceController = new ServiceController();
		Controller taskController = new TaskController();
		for (String path : ServiceController.URIS) {
			regist(path, serviceController);
		}
		for (String path : TaskController.URIS) {
			regist(path, taskController);
		}
	}
	
	public Controller getController (String path) {
		return controllerMap.get(path);
	}
	
	public void regist(String path, Controller controller){
		controllerMap.put(path, controller);
	} 
}
