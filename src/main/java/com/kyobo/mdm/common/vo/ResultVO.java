package com.kyobo.mdm.common.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultVO {

	private Map<String, Object> resultMap = new HashMap<String, Object>();

	// �����޼���
	public final static String STATUS_SUCESS = "S";

	// ���� �޼���
	public final static String STATUS_SUCESS_MESSAGE = "���� ó���Ǿ����ϴ�.";

	// �����޼���
	public final static String STATUS_ERROR = "E";

	// �⺻ ���� �� �ڵ�
	public final static String STATUS_ERROR_DEFAULT_DETAIL_CODE = "E9999";

	// �����޼���
	public final static String STATUS_ERROR_MESSAGE = "ó�� ���� ������ �߻��Ǿ����ϴ�.";

	// ���޼���
	public final static String STATUS_WARNING = "W";

	// ���޼���
	public final static String STATUS_WARNING_MESSAGE = "ó�� ���� ������ �߻��Ǿ����ϴ�.";

	// �⺻(map Ÿ��) �������� view
	public final static String VIEW_DEFAULT = "wqView";

	// ������� ���� �޼��� key��
	public final static String MESSAGE_KEY = "rsMsg";

	// viewType�� VIEW_STRING �� ��� �����ϴ� key
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
			setMsg(STATUS_SUCESS);
		}

		return resultMap;
	}

	/**
	 * �޼��� ó�� - ���� �⺻ �޼��� ó��
	 */
	public void setMsg(String status) {
		String msg = "";
		if (status == STATUS_ERROR) {
			msg = STATUS_ERROR_MESSAGE;
		} else if (status == STATUS_SUCESS) {
			msg = STATUS_SUCESS_MESSAGE;
		} else if (status == STATUS_WARNING) {
			msg = STATUS_WARNING_MESSAGE;
		}
		setMsg(status, msg);
	}

	/**
	 * �޼��� ó��
	 */
	public void setMsg(String status, String message) {
		setMsg(status, message, null);
	}

	/**
	 * �޼��� ó��
	 */
	public void setMsg(String status, String message, Exception ex) {

		Map<String, Object> result = new HashMap<String, Object>();

		if (status.equals(STATUS_SUCESS)) {
			result.put("statusCode", STATUS_SUCESS);
			result.put("message", getDefaultStatusMessage(message, STATUS_SUCESS_MESSAGE));
		} else if (status.equals(STATUS_WARNING)) {
			result.put("statusCode", STATUS_WARNING);
			result.put("message", getDefaultStatusMessage(message, STATUS_WARNING_MESSAGE));
		} else if (status.equals(STATUS_ERROR)) {
			setErrorMsg(STATUS_ERROR_DEFAULT_DETAIL_CODE, message, ex);
			return;
		}

		if (ex != null) {
			result.put("messageDetail", ex.getMessage());
		}

		resultMap.put(MESSAGE_KEY, result);
	}

	/**
	 * ���� �޼��� ó��
	 */
	public void setErrorMsg(String errorCode, String message) {
		setErrorMsg(errorCode, message, null);
	}

	/**
	 * ���� �޼��� ó��
	 */
	public void setErrorMsg(String errorCode, String message, Exception ex) {
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("statusCode", STATUS_ERROR);
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