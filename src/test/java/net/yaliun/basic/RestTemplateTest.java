package net.yaliun.basic;

import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {

	public static void main(String[] args) {

		try{
			RestTemplate restTemplate = new RestTemplate();
			
			String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.add("X-Naver-Client-Id", "VJVClCFr082KdScP1qGM");
			headers.add("X-Naver-Client-Secret", "RRI_c3nur8");
			
			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
			parameters.add("source", "ko");
			parameters.add("target", "en");
			parameters.add("text", "몇살이에요?");
			
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters,headers);
			
			ResponseEntity<Map> response;
			
			URI uri = URI.create(apiURL);
			
		    response = restTemplate.postForEntity(uri, request, Map.class);		    
		    System.out.println(response);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
