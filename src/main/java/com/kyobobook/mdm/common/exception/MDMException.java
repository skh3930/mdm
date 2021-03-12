package com.kyobobook.mdm.common.exception;

public class MDMException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Object[] args;

	public MDMException(String msg) {
		super(String.format(msg));
	}

	public MDMException(String msg, Object[] args) {
		super(String.format(msg));
		this.args = args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public Object[] getArgs() {
		return args;
	}
}