/**
 * 
 */
package com.frain.main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frain.biz.QueryTicketLeft;

/**
 * @author kalven.meng
 *
 */
public class Main {
	private static Logger log = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		startUp();
	}
	
	public static void startUp() {
		final QueryTicketLeft query = new QueryTicketLeft();
		
		Runnable task = new Runnable() {
			 
			public void run() {
				// TODO Auto-generated method stub
				log.info("开始查询");
				query.queryTicket();
				log.info("查询结束");
			}
		};
		ScheduledExecutorService scheduleService = Executors.newSingleThreadScheduledExecutor();
		scheduleService.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
		log.info("系统启动成功");
	}
}
