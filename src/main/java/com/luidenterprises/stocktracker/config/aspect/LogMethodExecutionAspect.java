package com.luidenterprises.stocktracker.config.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogMethodExecutionAspect {
	
	@Before("@annotation(LogMethodExecution)")
	public void logMethodExecutionStart(JoinPoint joinPoint) throws Throwable {		
		log.info("[{}] Start: Args: {}", joinPoint.getSignature().getDeclaringTypeName(), java.util.Arrays.toString(joinPoint.getArgs()));		
	}
	@Order(1)
	@Around("@annotation(LogMethodExecution)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
	    long start 			= System.currentTimeMillis();
	    Object proceed 		= joinPoint.proceed();
	    long executionTime 	= System.currentTimeMillis() - start;
	    log.info("[{}]: Execution time: {}(ms)", joinPoint.getSignature().getDeclaringTypeName(), executionTime);
	    return proceed;
	}
	@Order(2)
	@AfterReturning(value="@annotation(LogMethodExecution)", returning="result")
	public void logMethodExecutionEnd(JoinPoint joinPoint, Object result) throws Throwable {		
		log.info("[{}]: Result: {}", joinPoint.getSignature().getDeclaringTypeName(), result);		
	}
	
	

}
