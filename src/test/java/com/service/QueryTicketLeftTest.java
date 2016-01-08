package com.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.frain.model.TicketDataInfo;
import com.frain.service.mail.MailService;
import com.frain.service.schedule.ScheduleService;
import com.frain.service.ticket.TicketQueryService;
import com.frain.util.TicInfoConstants;

public class QueryTicketLeftTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public void teste() {
		
		TicketQueryService service = new TicketQueryService();
		MailService mailService = new MailService();
		List<TicketDataInfo> tickets =  service.getTicketLeft();
		StringBuilder sb = new StringBuilder();
		for (TicketDataInfo ticketDataInfo : tickets) {
			if (StringUtils.isNotEmpty(ticketDataInfo.getSecretStr())) {
				Map<String, String> ticketInfo = ticketDataInfo.getQueryLeftNewDTO();
				String trainCode = ticketInfo.get(TicInfoConstants.STATION_TRAIN_CODE);
				if (!trainCode.startsWith("G") || !ticketDataInfo.isExistsZe()) continue;
				
				sb.append("车次 :").append(ticketInfo.get(TicInfoConstants.STATION_TRAIN_CODE))
				.append("  ")
				.append("发车时间:").append(ticketInfo.get(TicInfoConstants.START_TIME))
				.append("  ")
				.append("到达时间:").append(ticketInfo.get(TicInfoConstants.ARRIVE_TIME))
				.append("  ")
				.append("发车日期:").append(ticketInfo.get(TicInfoConstants.START_TRAIN_DATE))
				.append("  ")
				.append("二等座:").append(ticketInfo.get(TicInfoConstants.ZE_NUM))
				.append("  ")
				.append("一等座:").append(ticketInfo.get(TicInfoConstants.ZY_NUM))
				.append("  ")
				.append("商务座:").append(ticketInfo.get(TicInfoConstants.SWZ_NUM));
				sb.append("\n");
			}
		}
//		mailService.sendMail("邮票提醒", sb.toString());
		System.out.println(sb);
		
	}
	@Test
	public void test() {
		ScheduleService schduleService = ScheduleService.getInstance();
		schduleService.start();
		try {
			Thread.sleep(1000*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
