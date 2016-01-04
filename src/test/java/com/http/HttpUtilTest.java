/**
 * 
 */
package com.http;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.frain.util.http.HttpUtils;

/**
 * @author kalven.meng
 *
 */
public class HttpUtilTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		HttpUtils httpUtil = HttpUtils.getInstance();
		String uri = "https://kyfw.12306.cn/otn/leftTicket/queryT?leftTicketDTO.train_date=2016-02-05&leftTicketDTO.from_station=SHH&leftTicketDTO.to_station=BBH&purpose_codes=ADULT";
		try {
			String result = httpUtil.execGet(uri);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
