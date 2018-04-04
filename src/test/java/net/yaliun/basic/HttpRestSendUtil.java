package net.yaliun.basic;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;

/**
 * 
keytool -list -keystore cacerts -storepass changeit

keytool -list -keystore tomcat.keystore -storepass change

keytool -delete -keystore tomcat.keystore -storepass change -alias google

keytool -importcert -keystore tomcat.keystore -storepass change -alias egov1 -file ca.open.egov.kz.cer

keytool -importcert -keystore tomcat.keystore -storepass change -trustcacerts -alias egov -file ca.open.egov.kz.cer

keytool -importcert -keystore tomcat.keystore -storepass change -trustcacerts -alias google -file google.cer
keytool -importcert -keystore tomcat.keystore -storepass change -trustcacerts -alias comodo -file comodo.cer


 * 
 */

@Component

public class HttpRestSendUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpRestSendUtil.class);
	
//	@Value("#{property['baseUrl']}")
//	private String vtnBaseUrl;
//
//	@Value("#{property['middleUrl']}")
//	private String vtnMiddleUrl;
//
//	@Value("#{property['keyStoreFile']}")
//	private String keyStoreFile;
//
//	@Value("#{property['tlsPasswd']}")
//	private String tlsPasswd;


	private String keyStoreFile;
	private String tlsPasswd;


	public ResponseEntity<String> restSendResEntity() throws Exception {

		//String url = "https://open.egov.kz";
		String url = "https://localhost:8443/docs/setup.html";
		//String url = "https://google.co.kr";
		//String url = "https://openapi.naver.com/v1/papago/n2mt";

		ResponseEntity<String> response = null;

		try {

			RestTemplate restTemplate = new RestTemplate(gteHttpsRequetFactory(url));
			//response = restTemplate.postForEntity(url, xmlObject, String.class);
			
			response = restTemplate.postForEntity(url, "", String.class);
			
			logger.debug("response : {}", response);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return response;
	}


	public HttpComponentsClientHttpRequestFactory gteHttpsRequetFactory(String url) throws Exception {

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		requestFactory.setReadTimeout(5000); // timeout 5sec
		requestFactory.setConnectTimeout(5000); // timeout 5sec

		FileInputStream instream = null;

		try {

			// https 요청일시

			if (url.toUpperCase().contains("HTTPS")) {

				KeyStore clientKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
				File file = new File("C:\\Users\\yaliun\\ssl\\truststore.jks");
				//File file = new File("C:/Program Files/Java/jdk1.8.0_121/jre/lib/security/cacerts");

				instream = new FileInputStream(file);
				tlsPasswd = "changeit";

				clientKeyStore.load(instream, tlsPasswd.toCharArray());

				logger.debug("## keystore : " + file.exists() + " / " + file.getName());
				logger.debug("## keystore : " + clientKeyStore.getType());
				logger.debug("## url : " + url);

				SSLContext sslContest = SSLContexts.custom()
						.loadTrustMaterial(clientKeyStore, null)
						.loadKeyMaterial(clientKeyStore, tlsPasswd.toCharArray())
						.useProtocol("TLSv1.2") // TLS 1.2
						.build();

				SSLConnectionSocketFactory sslsf =

						new SSLConnectionSocketFactory(
								sslContest, new String[] { "TLSv1.2" }
								, sslContest.getDefaultSSLParameters().getCipherSuites()
								, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

				CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslsf).build();

				requestFactory.setHttpClient(client);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		} finally {
			if (null != instream)
				instream.close();
		}
		;
		return requestFactory;
	}
	
	public static void main(String[] args) {
		HttpRestSendUtil rest = new HttpRestSendUtil();
		try{
			rest.restSendResEntity();	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
