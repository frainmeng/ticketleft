/**
 * 
 */
package com.frain.model;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.frain.util.TicInfoConstants;

/**
 * @author kalven.meng
 *
 */
public class TicketDataInfo {
	private String secretStr ;
	private String buttonTextInfo;
	private Map<String, String> queryLeftNewDTO;
	
	/**
	 * 是否有二等座
	 * @return
	 */
	public boolean isExistsZe () {
		if (StringUtils.isEmpty(secretStr)) return false;
		String zeNum = queryLeftNewDTO.get(TicInfoConstants.ZE_NUM);
		if (StringUtils.isNotEmpty(zeNum) && !"无".equals(zeNum) && !"--".equals(zeNum)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return the secretStr
	 */
	public String getSecretStr() {
		return secretStr;
	}
	/**
	 * @param secretStr the secretStr to set
	 */
	public void setSecretStr(String secretStr) {
		this.secretStr = secretStr;
	}
	/**
	 * @return the buttonTextInfo
	 */
	public String getButtonTextInfo() {
		return buttonTextInfo;
	}
	/**
	 * @param buttonTextInfo the buttonTextInfo to set
	 */
	public void setButtonTextInfo(String buttonTextInfo) {
		this.buttonTextInfo = buttonTextInfo;
	}
	/**
	 * @return the queryLeftNewDTO
	 */
	public Map<String, String> getQueryLeftNewDTO() {
		return queryLeftNewDTO;
	}
	/**
	 * @param queryLeftNewDTO the queryLeftNewDTO to set
	 */
	public void setQueryLeftNewDTO(Map<String, String> queryLeftNewDTO) {
		this.queryLeftNewDTO = queryLeftNewDTO;
	}
	
}
