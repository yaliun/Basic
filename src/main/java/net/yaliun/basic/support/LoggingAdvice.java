package net.yaliun.basic.support;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAdvice {

	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	
	@Before("execution(* net.yaliun.basic.controller.*.*(..)) or execution(* net.yaliun.basic.service.*.*(..)) or execution(* net.yaliun.basic.persistence.*.*(..))")	
	public void startLog(JoinPoint jp){
		logger.info("------------------------------------------------------------------------------");
		logger.info("Class Name : [{}]", jp.getSignature().getDeclaringTypeName());
		logger.info("Method : [{}]", jp.getSignature().getName());
		logger.info("Args : {}", Arrays.toString(jp.getArgs()));
		logger.info("------------------------------------------------------------------------------\n");
	}
}
