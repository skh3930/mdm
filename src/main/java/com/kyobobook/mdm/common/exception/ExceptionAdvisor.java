package com.kyobobook.mdm.common.exception;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kyobobook.mdm.common.contstants.MDMConstants;
import com.kyobobook.mdm.common.vo.Result;

@RestControllerAdvice
public class ExceptionAdvisor {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(Exception.class)
	protected Map<String, Object> handleException(Exception ex) {
		ex.printStackTrace();

		Result result = new Result();
		result.setMsg(MDMConstants.STATUS_ERROR, ex);
	    return result.getResult();
	}
	
	@SuppressWarnings("finally")
	@ExceptionHandler(MDMException.class)
	protected Map<String, Object> handleMDMException(MDMException ex) {
		Result result = new Result();
		String msg = "";
		
		try {
			msg = messageSource.getMessage(ex.getMessage(), ex.getArgs(), Locale.KOREAN);
		} catch (NoSuchMessageException nex) {
			msg = Result.STATUS_ERROR_MESSAGE;
		} finally {
			result.setMsg(MDMConstants.STATUS_ERROR, msg);
			return result.getResult();
		}
	}
}
