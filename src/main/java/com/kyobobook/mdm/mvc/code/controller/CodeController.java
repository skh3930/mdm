package com.kyobobook.mdm.mvc.code.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyobobook.mdm.common.contstants.MDMConstants;
import com.kyobobook.mdm.common.util.BeanUtil;
import com.kyobobook.mdm.common.vo.Result;
import com.kyobobook.mdm.mvc.code.process.CodeProcess;
import com.kyobobook.mdm.mvc.code.service.CodeSearchService;
import com.kyobobook.mdm.mvc.code.service.CodeService;
import com.kyobobook.mdm.mvc.code.vo.CodeDtl;
import com.kyobobook.mdm.mvc.code.vo.CodeDtlRlts;
import com.kyobobook.mdm.mvc.code.vo.CodeGrp;
import com.kyobobook.mdm.mvc.code.vo.CodeRlts;

@RestController
@RequestMapping("/code")
public class CodeController {

	@Autowired
	private CodeService codeService;

	@Autowired
	private CodeProcess codeProcess;

	@Autowired
	private CodeSearchService codeSearchService;

	/**
	 * 공통코드 등록
	 * 
	 * @param Map
	 * @return Map
	 */
	@PostMapping("/registerCode")
	public Map<String, Object> registerCode(@RequestBody Map<String, Object> param) {

		List<CodeGrp> codeGrps = BeanUtil.mapToBeanList((List) param.get("dlt_codeGrp"), CodeGrp.class); // 코드그룹
		List<CodeDtl> codeDtls = BeanUtil.mapToBeanList((List) param.get("dlt_codeDtl"), CodeDtl.class); // 코드상세
		List<CodeRlts> codeRltss = BeanUtil.mapToBeanList((List) param.get("dlt_codeRlts"), CodeRlts.class); // 코드관계
		List<CodeDtlRlts> codeDtlRltss = BeanUtil.mapToBeanList((List) param.get("dlt_codeDtlRlts"), CodeDtlRlts.class); // 코드상세관계

		List<CodeGrp> codeGrpList = codeProcess.registerCodes(codeGrps, codeDtls, codeRltss, codeDtlRltss);

		Result result = new Result();
		result.setData("ds_codeGrp", codeGrpList);
		result.setMsg(MDMConstants.STATUS_SUCESS);
		
		return result.getResult();
	}

	/**
	 * 공통코드 수정
	 * 
	 * @param Map
	 * @return Map
	 */
	@PostMapping("/modifyCode")
	public Map<String, Object> modifyCode(@RequestBody Map<String, Object> param) {
		Result result = new Result();
		result.setMsg(MDMConstants.STATUS_SUCESS);

		return result.getResult();
	}
}
