/**
 * 
 */
package com.frain.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author kalven.meng
 *
 */
public class PropertyUtils {
	private static Log log = LogFactory.getLog(PropertyUtils.class);
	private static Map<String, Properties> propsMap = new HashMap<String, Properties>();
	static {
		Properties mailProps = new Properties();
		Properties httpProps = new Properties();
		Properties defaultTaskProps = new Properties();
		try {
			mailProps.load(PropertyUtils.class.getClassLoader().getResourceAsStream(ConfConstants.MAIL_CONFIG));
			httpProps.load(PropertyUtils.class.getClassLoader().getResourceAsStream(ConfConstants.HTTP_CONFIG));
			defaultTaskProps.load(PropertyUtils.class.getClassLoader().getResourceAsStream(ConfConstants.DEFAULT_TASK_CONFIG));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("初始化配置文件异常", e);
		}
		propsMap.put(ConfConstants.MAIL_CONFIG, mailProps);
		propsMap.put(ConfConstants.HTTP_CONFIG, httpProps);
		propsMap.put(ConfConstants.DEFAULT_TASK_CONFIG, defaultTaskProps);
	}
	
	/**
	 * 获取配置文件内容
	 * @param Key 配置文件名
	 * @return 
	 */
	public static Properties getProps (String Key) {
		return propsMap.get(Key);
	}
	
	/**
	 * 获取配置项值
	 * @param propsKey 配置文件名
	 * @param propKey 配置项key
	 * @return not exists return null, else the value value of the key
	 */
	public static String getPropValue (String propsKey, String propKey) {
		
		return propsMap.get(propsKey) == null ? null : propsMap.get(propsKey).getProperty(propKey);
		
	}
	
	
}
