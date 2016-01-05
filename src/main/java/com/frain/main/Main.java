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
				log.info("��ʼ��ѯ");
				query.queryTicket();
				log.info("��ѯ����");
			}
		};
		ScheduledExecutorService scheduleService = Executors.newSingleThreadScheduledExecutor();
		scheduleService.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
		log.info("ϵͳ�����ɹ�");
	}
}
