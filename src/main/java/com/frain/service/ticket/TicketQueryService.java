/**
 * 
 */
package com.frain.service.ticket;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.frain.model.QueryTask;
import com.frain.model.TicketDataInfo;
import com.frain.model.TicketLeftQueryDTO;
import com.frain.util.ConfConstants;
import com.frain.util.PropertyUtils;
import com.frain.util.http.HttpUtils;

/**
 * @author kalven.meng
 *
 */
public class TicketQueryService {
	private static Logger log = LoggerFactory.getLogger(TicketQueryService.class);
	private static HttpUtils httpUtils = HttpUtils.getInstance();
	
	private final static String TICKET_QUERY_URI = "ticket.query.uri";
	
	public List<TicketDataInfo> getTicketLeft () {
		String uri = PropertyUtils.getPropValue(ConfConstants.HTTP_CONFIG, TICKET_QUERY_URI);
		String result = httpUtils.execGet(uri);
		TicketLeftQueryDTO ticketLeftQueryDTO = JSON.parseObject(result, TicketLeftQueryDTO.class);
		log.info("票务查询返回信息：" + ticketLeftQueryDTO.toString());
		String[] messages = ticketLeftQueryDTO.getMessages();
		if (null != messages && messages.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (String message : messages) {
				sb.append(message).append("  ");
			}
			log.info("票务查询失败：{}" ,sb.toString());
		}
		return ticketLeftQueryDTO.getData();
	}
	
	public List<TicketDataInfo> getTicketLeft (QueryTask queryTask) {
		List<TicketDataInfo> list = null;
		try {
			String uri = queryTask.getReqUri();
			String result = httpUtils.execGet(uri);
			TicketLeftQueryDTO ticketLeftQueryDTO = JSON.parseObject(result, TicketLeftQueryDTO.class);
			log.info(queryTask.getTaskName()+"票务查询返回信息：" + ticketLeftQueryDTO.toString());
			String[] messages = ticketLeftQueryDTO.getMessages();
			if (null != messages && messages.length > 0) {
				StringBuilder sb = new StringBuilder();
				for (String message : messages) {
					sb.append(message).append("  ");
				}
				log.info(queryTask.getTaskName()+"票务查询失败：{}" ,sb.toString());
			}
			list = ticketLeftQueryDTO.getData();
		} catch (Exception e) {
			log.error(queryTask.getTaskName()+"任务异常", e);
		}
		return list;
	}
	
}
