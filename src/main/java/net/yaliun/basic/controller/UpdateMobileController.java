package net.yaliun.basic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.yaliun.basic.domain.UpdateMobileReqVO;
import net.yaliun.basic.domain.UpdateMobileResVO;

@RestController
public class UpdateMobileController {
	private static final Logger logger = LoggerFactory.getLogger(UpdateMobileController.class);

	@RequestMapping(value="/api/updateMobile", method=RequestMethod.POST)
	public UpdateMobileResVO updateMobile(@RequestBody UpdateMobileReqVO reqVo){
		
		logger.info("/api/updateMobile start");
		logger.info("reqVo : {}",reqVo.toString());
		
		UpdateMobileResVO resVo = new UpdateMobileResVO();
		resVo.setSessionId("session12345");
		resVo.setMsisdn("821043400369");
		
		logger.info("resVo : {}",resVo.toString());

		logger.info("/api/updateMobile end");
		
		return resVo;
	}
	
	@RequestMapping(value="/api/a", method=RequestMethod.POST)
	public ResponseEntity<String> handleRequest(HttpEntity<String> entity){
		logger.info("/api/a start");
		logger.info("request body : {}",entity.getBody());
		
		HttpHeaders headers = entity.getHeaders();
		
		logger.info("request head : {}", headers);
		
		//HttpEntity<String> responseEntity = new HttpEntity<String>("my response body");
		
		ResponseEntity<String> resEntity = new ResponseEntity<String>("okc response body", HttpStatus.OK);
		
		return resEntity;
	}
	
	@RequestMapping(value="/api/test", method=RequestMethod.GET)
	public String test() throws Exception{
		logger.info("/api/test start");
		throw new Exception("Exception Test");
	}
}
