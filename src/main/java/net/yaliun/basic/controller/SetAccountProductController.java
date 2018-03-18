package net.yaliun.basic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.yaliun.basic.domain.SetAccountProductReqVO;
import net.yaliun.basic.domain.SetAccountProductResVO;
import net.yaliun.basic.service.SetAccountProductService;

@RestController
public class SetAccountProductController {
	private static final Logger logger = LoggerFactory.getLogger(SetAccountProductController.class);
	
	@Autowired
	private SetAccountProductService service;
	
	@RequestMapping(value="/api/setAccountProduct", method=RequestMethod.POST)
	public SetAccountProductResVO setAccountProduct(@RequestBody SetAccountProductReqVO reqVo){
		
		logger.info("/api/setAccountProduct start");
		logger.info("reqVo : {}",reqVo.toString());
		
		service.addProduct(reqVo);
		
		int sum = service.sum();
		
		SetAccountProductResVO resVo = new SetAccountProductResVO();
		if(sum < 10){
			resVo.setResult("SUCCESS");	
		}else{
			resVo.setResult("FAIL");
		}		
		
		logger.info("resVo : {}",resVo.toString());

		logger.info("/api/setAccountProduct end");
		
		return resVo;
	}

}
