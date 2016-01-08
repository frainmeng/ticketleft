/**
 * 
 */
package com.frain.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frain.main.Main;

/**
 * @author kalven.meng
 *
 */
public class StartupListener implements ServletContextListener {
	private static Logger log = LoggerFactory.getLogger(StartupListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		log.info("服务初始化成功");
//		Main.startUp();
	}

}
