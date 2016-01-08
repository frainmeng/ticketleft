package com.frain.web.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.alibaba.fastjson.JSON;
import com.frain.main.Main;
import com.frain.model.RespModel;
import com.frain.web.controller.Controller;
import com.frain.web.controller.ControllerFactory;

public class DispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5338071057209724875L;
	
	private static Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
	
	private ControllerFactory controllerFactory = ControllerFactory.getInstance();
	
	private static String message = "";
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		setMessage("");
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String controlStr = req.getParameter("controlStr");
		dispatcher(req, resp);
		/*
		if (!"kalvenmeng".equals(userName) || !"111111".equals(password)) {
			message="username or password wrong!!";
		} else {
			if ("start".equalsIgnoreCase(controlStr)) {
				Main.startUp();
				log.info("启动服务...");
			} else if("stop".equalsIgnoreCase(controlStr)) {
				Main.shutdown();
				log.info("停止服务...");
			} else {
				message = Main.getStatus() ? "Service is Running now !" : "Service is Stoped now"; 
			}
		}
		PrintWriter out = resp.getWriter();
		out.print(getServiceStatus());
		*/
	}
	
	private void dispatcher(HttpServletRequest request, HttpServletResponse response) {
		String path= request.getServletPath();
		path = path.replace(".do", "");
		try {
			Controller controller = controllerFactory.getController(path);
			if (null != controller) {
				RespModel respModel = controller.service(path, request, response);
				String result = JSON.toJSONString(respModel);
				PrintWriter out = response.getWriter();
				out.print(result);
				out.flush();
				out.close();
			} else {
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		
	}
	
	private String getServiceStatus() {
		Map<String, String> resultMap = new HashMap<String, String>(); 
		resultMap.put("status", Main.getStatus() ? "running" : "stoped");
		resultMap.put("message", message);
		return JSON.toJSONString(resultMap);
	}
	
	public static void setMessage (String messageStr) {
		message = messageStr;
	}
}
