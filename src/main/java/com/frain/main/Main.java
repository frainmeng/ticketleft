/**
 * 
 */
package com.frain.main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frain.service.ticket.QueryTicketLeftService;

/**
 * @author kalven.meng
 *
 */
public class Main {
	private static Logger log = LoggerFactory.getLogger(Main.class);
	private static boolean status = false;
	private static ScheduledExecutorService scheduleService = null;
	
	public static void main(String[] args) {
		startUp();
	}
	
	public static void startUp() {
		scheduleService = Executors.newSingleThreadScheduledExecutor();
		final QueryTicketLeftService query = new QueryTicketLeftService();
		
		Runnable task = new Runnable() {
			 
			public void run() {
				// TODO Auto-generated method stub
				log.info("开始查询");
//				query.queryTicket();
				log.info("查询结束");
			}
		};
		scheduleService.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
		status = true;
		log.info("服务启动成功");
	}
	
	public static void shutdown () {
		if (null != scheduleService) {
			scheduleService.shutdown();
			scheduleService = null;
		}
		status = false;
		log.info("服务停止成功");
	}
	
	public static boolean getStatus() {
		return status;
	}
}
