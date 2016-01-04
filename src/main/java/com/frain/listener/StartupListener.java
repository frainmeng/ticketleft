/**
 * 
 */
package com.frain.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.frain.main.Main;

/**
 * @author kalven.meng
 *
 */
public class StartupListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		Main.startUp();
	}

}
