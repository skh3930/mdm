package com.kyobo.mdm.code.vo;

import com.kyobo.mdm.common.vo.CommonVO;

import lombok.Data;

@Data
public class CodeVO extends CommonVO{

	private String codeId = "CODE";
	private String codeIdNm = "테스트코드명";
	private int codeLength = 10;
	private String codeIdDesc = "테스트ID상세";
	private String tableNm = "테스트테이블명";
	private String codeRltn = "테스트코드관계";
	private String prntCodeId = "테스트부모코드ID";
	private String prntCodeNm = "테스트부모코드명";
	private String codeVal = "테스트코드값";
	private String codeValNm = "테스트코드값명";
	private String priority = "테스트우선순위";
	private String codeValDesc = "테스트코드값상세";
	
	public CodeVO(String codeId) {
		this.codeId = codeId;
	}
}
