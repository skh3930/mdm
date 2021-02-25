package com.kyobo.mdm.common.exception;

public class MDMException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public MDMException(String msg) {
        super(String.format(msg));
       
    }
}