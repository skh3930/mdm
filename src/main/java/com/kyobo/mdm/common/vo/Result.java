package com.kyobo.mdm.common.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kyobo.mdm.common.contstants.MDMConstants;

public class Result {

	private Map<String, Object> resultMap = new HashMap<String, Object>();

	public final static String STATUS_SUCESS_MESSAGE = "정상 처리되었습니다.";
	public final static String STATUS_ERROR_MESSAGE = "처리 도중 오류가 발생되었습니다.";
	public final static String STATUS_WARNING_MESSAGE = "처리 도중 오류가 발생되었습니다.";
	
	// 기본 에러 상세 코드
	private final static String STATUS_ERROR_DEFAULT_DETAIL_CODE = "E9999";

	// websquare view setting
	public final static String VIEW_DEFAULT = "wqView";
	public final static String MESSAGE_KEY = "rsMsg";
	public final static String RESULT_KEY_DEFAULT = "result";

	public void setData(String id, String data) {
		resultMap.put(id, data);
	}

	public void setData(String id, Map data) {
		resultMap.put(id, data);
	}

	public void setData(String id, List data) {
		resultMap.put(id, data);
	}
	
	public void setData(String id, CommonVO data) {
		resultMap.put(id, data);
	}

	public Map<String, Object> getResult() {
		if (resultMap.get(MESSAGE_KEY) == null) {
			setMsg(MDMConstants.STATUS_SUCESS);
		}

		return resultMap;
	}

	public void setMsg(String status) {
		String msg = "";
		if (status.equals(MDMConstants.STATUS_ERROR)) {
			msg = STATUS_ERROR_MESSAGE;
		} else if (status.equals(MDMConstants.STATUS_SUCESS)) {
			msg = STATUS_SUCESS_MESSAGE;
		} else if (status.equals(MDMConstants.STATUS_WARNING)) {
			msg = STATUS_WARNING_MESSAGE;
		}
		setMsg(status, msg);
	}

	public void setMsg(String status, String message) {
		setMsg(status, message, null);
	}
	
	public void setMsg(String status, Exception ex) {
		setMsg(status, STATUS_ERROR_MESSAGE , null);
	}

	public void setMsg(String status, String message, Exception ex) {

		Map<String, Object> result = new HashMap<String, Object>();

		if (status.equals(MDMConstants.STATUS_SUCESS)) {
			result.put("statusCode", MDMConstants.STATUS_SUCESS);
			result.put("message", getDefaultStatusMessage(message, STATUS_SUCESS_MESSAGE));
		} else if (status.equals(MDMConstants.STATUS_WARNING)) {
			result.put("statusCode", MDMConstants.STATUS_WARNING);
			result.put("message", getDefaultStatusMessage(message, STATUS_WARNING_MESSAGE));
		} else if (status.equals(MDMConstants.STATUS_ERROR)) {
			setErrorMsg(STATUS_ERROR_DEFAULT_DETAIL_CODE, message, ex);
			return;
		}

		if (ex != null) {
			result.put("messageDetail", ex.getMessage());
		}

		resultMap.put(MESSAGE_KEY, result);
	}

	public void setErrorMsg(String errorCode, String message) {
		setErrorMsg(errorCode, message, null);
	}

	public void setErrorMsg(String errorCode, String message, Exception ex) {
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("statusCode", MDMConstants.STATUS_ERROR);
		result.put("errorCode", errorCode);
		result.put("message", getDefaultStatusMessage(message, STATUS_ERROR_MESSAGE));

		if (ex != null) {
			result.put("messageDetail", "" + ex.getMessage());
		}
		resultMap.put(MESSAGE_KEY, result);
	}

	public String getDefaultStatusMessage(String message, String defMessage) {
		if (message == null) {
			return defMessage;
		}
		return message;
	};
}