/**
 * 
 */
package com.frain.util.http;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import com.frain.util.Constants;
import com.frain.util.PropertyUtils;



/**
 * @author kalven.meng
 *
 */
public class HttpUtils {
	private static Log log = LogFactory.getLog(HttpUtils.class);
	
	private static String HTTP_CONN_MAX_TOTAL = "http.conn.max.total";
	
	private static String HTTP_CONN_MAX_PERROUTE = "http.conn.max.perroute";
	
	private static HttpUtils self = new HttpUtils();
	
	private boolean isServiceOK = false;
	
	private Properties props = null ;
	
	private PoolingHttpClientConnectionManager clientManager = null; 
	
	private HttpUtils() {
		isServiceOK = init();
	}
	
	public static HttpUtils getInstance () {
		return self;
	}
	
	private synchronized boolean init () {
		if (isServiceOK) return isServiceOK;
		try {
			destroy();
			props = PropertyUtils.getProps(Constants.HTTP_CONFIG);
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
					.<ConnectionSocketFactory> create()
					.register("https", new SSLConnectionSocketFactory(SSLContexts.custom()
							.loadTrustMaterial(null, new TrustStrategy() {
									public boolean isTrusted(X509Certificate[] chain, String authType)
											throws CertificateException {
										// TODO Auto-generated method stub
										return true;
									}
							}).useProtocol("SSL")
							.build()
					))
					.register("http", PlainConnectionSocketFactory.getSocketFactory())
					.build();
			clientManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			clientManager.setMaxTotal(Integer.parseInt(props.getProperty(HTTP_CONN_MAX_TOTAL)));
			clientManager.setDefaultMaxPerRoute(Integer.parseInt(props.getProperty(HTTP_CONN_MAX_PERROUTE)));
			isServiceOK = true;
		} catch (Exception e) {
			// TODO: handle exception
			isServiceOK = false;
			log.error("≥ı ºªØ ß∞‹", e);
		}
		return isServiceOK;
	}
	
	public void destroy() {
		if (clientManager != null) {
			clientManager.shutdown();
			isServiceOK = false;
		}
	}
	
	
	public CloseableHttpClient getHttpClient () {
		if (!isServiceOK && !init()) return null; 
		
		CloseableHttpClient httpClient = HttpClients.custom()
		        .setConnectionManager(clientManager)
		        .build();
		
		return httpClient;
	}
	
	public String execGet(String uri){
		String result = null;
		HttpGet httpget = new HttpGet(uri);
		try {
			CloseableHttpClient httpclient = getHttpClient();
			CloseableHttpResponse response = httpclient.execute(httpget);
			HttpEntity httpEntity = response.getEntity();
			result = EntityUtils.toString(httpEntity);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("÷¥––«Î«Û ß∞‹", e);
		} finally {
			httpget.releaseConnection();
		}
		return result;
	}
	
}
