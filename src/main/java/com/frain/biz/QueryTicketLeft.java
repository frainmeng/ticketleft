/**
 * 
 */
package com.frain.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frain.model.TicketDataInfo;
import com.frain.service.mail.MailService;
import com.frain.service.ticket.TicketQueryService;
import com.frain.util.TicInfoConstants;

/**
 * @author kalven.meng
 *
 */
public class QueryTicketLeft {
	private static Logger log = LoggerFactory.getLogger(QueryTicketLeft.class);
	private TicketQueryService ticketService ;
	private MailService mailService ;
	private Map<String, String> ticketRequireMap ;
	public QueryTicketLeft () {
		init();
	}
	private void  init () {
		ticketService = new TicketQueryService();
		mailService = new MailService();
		ticketRequireMap = new HashMap<String, String>();
		ticketRequireMap.put("G108", "G108");
		ticketRequireMap.put("D9580", "D9580");
		ticketRequireMap.put("D4502", "D4502");
		ticketRequireMap.put("G114", "G114");
		ticketRequireMap.put("G230", "G230");
		ticketRequireMap.put("G116", "G116");
		ticketRequireMap.put("G124", "G124");
		ticketRequireMap.put("G1228", "G1228");
		ticketRequireMap.put("G7296", "G7296");
		ticketRequireMap.put("G134", "G134");
	}
	
	public void queryTicket(){
		
		try {
			List<TicketDataInfo> tickets =  ticketService.getTicketLeft();
			if (null == tickets) {
				log.info("û�в�ѯ���κβ�Ʊ");
				return;
			}
			StringBuilder sb = new StringBuilder();
			for (TicketDataInfo ticketDataInfo : tickets) {
				if (ticketDataInfo.isExistsZe()) {
					Map<String, String> ticketInfo = ticketDataInfo.getQueryLeftNewDTO();
					String trainCode = ticketInfo.get(TicInfoConstants.STATION_TRAIN_CODE);
					if (!ticketRequireMap.containsKey(trainCode)) continue;
					sb.append("���� :").append(ticketInfo.get(TicInfoConstants.STATION_TRAIN_CODE))
					.append("  ")
					.append("����ʱ��:").append(ticketInfo.get(TicInfoConstants.START_TIME))
					.append("  ")
					.append("����ʱ��:").append(ticketInfo.get(TicInfoConstants.ARRIVE_TIME))
					.append("  ")
					.append("��������:").append(ticketInfo.get(TicInfoConstants.START_TRAIN_DATE))
					.append("  ")
					.append("������:").append(ticketInfo.get(TicInfoConstants.ZE_NUM))
					.append("   ")
					.append("һ����:").append(ticketInfo.get(TicInfoConstants.ZY_NUM))
					.append("   ")
					.append("������:").append(ticketInfo.get(TicInfoConstants.SWZ_NUM));
					sb.append("\n");
				}
			}
			String mailText = sb.toString();
			if (StringUtils.isNotEmpty(mailText)) {
				mailService.sendMail("Ʊ������", sb.toString());
				log.info("��ѯ����Ʊ��"+mailText);
			} else {
				log.info("û�в鵽��Ʊ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("��ѯʧ��", e);
		}
	}
	
}
