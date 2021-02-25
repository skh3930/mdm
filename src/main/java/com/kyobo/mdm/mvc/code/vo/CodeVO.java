package com.kyobo.mdm.mvc.code.vo;

import com.kyobo.mdm.common.vo.CommonVO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CodeVO extends CommonVO {

	private String codeId;
	private String codeIdNm;
	private int codeLength;
	private String codeIdDesc;
	private String tableNm;
	private String codeRltn;
	private String prntCodeId;
	private String prntCodeNm;
	private String codeVal;
	private String codeValNm;
	private String priority;
	private String codeValDesc;
	
	public CodeVO(String codeId) {
		this.codeId = codeId;
	}
}