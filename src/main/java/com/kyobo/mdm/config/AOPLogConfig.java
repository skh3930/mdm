package com.kyobo.mdm.config;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@Aspect
public class AOPLogConfig {
	@Around("execution(* com.kyobo.mdm.mvc..*Controller.*(..))")
	public Object logController(ProceedingJoinPoint pjp) throws Throwable {

		log.debug("{}.{} - start", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
		log.debug("request data : {}", Arrays.toString(pjp.getArgs()));

		Object result = pjp.proceed();

		log.debug("response data : {}", result);
		log.debug("{}.{} - end", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), result);

		return result;
	}
}
