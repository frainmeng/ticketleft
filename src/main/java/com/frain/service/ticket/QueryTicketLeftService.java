/**
 * 
 */
package com.frain.service.ticket;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frain.model.QueryTask;
import com.frain.model.TicketDataInfo;
import com.frain.service.mail.MailService;
import com.frain.util.TicInfoConstants;

/**
 * @author kalven.meng
 *
 */
public class QueryTicketLeftService {
	private static Logger log = LoggerFactory.getLogger(QueryTicketLeftService.class);
	private TicketQueryService ticketService ;
	private MailService mailService ;
	public QueryTicketLeftService () {
		init();
	}
	private void  init () {
		ticketService = new TicketQueryService();
		mailService = new MailService();
	}
	
	
	public void query(QueryTask queryTask){
		try {
			List<TicketDataInfo> tickets =  ticketService.getTicketLeft(queryTask);
			if (null == tickets) {
				log.info("û�в�ѯ����Ʊ��Ϣ");
				return;
			}
			StringBuilder sb = new StringBuilder();
			for (TicketDataInfo ticketDataInfo : tickets) {
				if (ticketDataInfo.isExistsZe()) {
					Map<String, String> ticketInfo = ticketDataInfo.getQueryLeftNewDTO();
					String trainCode = ticketInfo.get(TicInfoConstants.STATION_TRAIN_CODE);
					if (!queryTask.getTrainCodeSet().contains(trainCode)) continue;
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
				mailService.sendMail("Ʊ������", sb.toString(),queryTask.getMailSendTo());
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
