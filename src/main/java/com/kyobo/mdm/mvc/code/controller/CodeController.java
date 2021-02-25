package com.kyobo.mdm.mvc.code.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyobo.mdm.common.contstants.MDMConstants;
import com.kyobo.mdm.common.vo.Result;
import com.kyobo.mdm.mvc.code.service.CodeService;
import com.kyobo.mdm.mvc.code.vo.CodeVO;

@RestController
@RequestMapping("/code")
public class CodeController {
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	MessageSource messageSource;
	
	@PostMapping
	public Map<String, Object> saveCode(@RequestBody Map<String, Object> param) {
		Result result = new Result();

		Map code = (Map) param.get("dma_code");
		
		//codeService.saveCode();
		
		result.setMsg(MDMConstants.STATUS_SUCESS);
		
		return result.getResult();
	}
	
	@GetMapping("/{id}")
	public Map<String, Object> getCodeById(@PathVariable("id") String id) {
		Result result = new Result();

		result.setData("dma_code", new CodeVO(id));
		result.setMsg(MDMConstants.STATUS_SUCESS);
	
		return result.getResult();
	}
}
