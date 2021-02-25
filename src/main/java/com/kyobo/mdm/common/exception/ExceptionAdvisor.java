package com.kyobo.mdm.common.exception;

import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kyobo.mdm.common.contstants.MDMConstants;
import com.kyobo.mdm.common.vo.Result;

@RestControllerAdvice
public class ExceptionAdvisor {
	
	@ExceptionHandler(Exception.class)
	protected Map<String, Object> handleException(Exception ex) {
		ex.printStackTrace();
		
		Result result = new Result();
		result.setMsg(MDMConstants.STATUS_ERROR, ex);
	    return result.getResult();
	}
	
	@ExceptionHandler(MDMException.class)
	protected Map<String, Object> handleMDMException(MDMException ex) {
		Result result = new Result();
		result.setMsg(MDMConstants.STATUS_ERROR, ex.getMessage());
	    return result.getResult();
	}
}
