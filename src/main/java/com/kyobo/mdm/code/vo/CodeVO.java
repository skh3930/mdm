package com.kyobo.mdm.code.vo;

import com.kyobo.mdm.common.vo.CommonVO;

import lombok.Data;

@Data
public class CodeVO extends CommonVO{

	private String codeId = "CODE";
	private String codeIdNm = "�׽�Ʈ�ڵ��";
	private int codeLength = 10;
	private String codeIdDesc = "�׽�ƮID��";
	private String tableNm = "�׽�Ʈ���̺��";
	private String codeRltn = "�׽�Ʈ�ڵ����";
	private String prntCodeId = "�׽�Ʈ�θ��ڵ�ID";
	private String prntCodeNm = "�׽�Ʈ�θ��ڵ��";
	private String codeVal = "�׽�Ʈ�ڵ尪";
	private String codeValNm = "�׽�Ʈ�ڵ尪��";
	private String priority = "�׽�Ʈ�켱����";
	private String codeValDesc = "�׽�Ʈ�ڵ尪��";
	
	public CodeVO(String codeId) {
		this.codeId = codeId;
	}
}
