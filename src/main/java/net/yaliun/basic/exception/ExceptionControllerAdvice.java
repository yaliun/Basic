package net.yaliun.basic.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e) {
		logger.info("Exception.class handler call");
		
		ResponseEntity<String> resEntity = new ResponseEntity<String>("Exception.class", HttpStatus.UNAUTHORIZED);
		
		return resEntity;
    }
}
