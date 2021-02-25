package com.kyobo.mdm.code.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyobo.mdm.code.service.CodeService;
import com.kyobo.mdm.code.vo.CodeVO;
import com.kyobo.mdm.common.vo.Result;


@RestController
@RequestMapping("/code")
public class CodeController {
	
	@Autowired
	CodeService codeService;
	
	@PostMapping
	public Map<String, Object> saveCode(@RequestBody Map<String, Object> param) {
		Result result = new Result();

		Map code = (Map) param.get("dma_code");
		
		codeService.saveCode();
		
		result.setMsg(result.STATUS_SUCESS);
		
		return result.getResult();
	}
	
	@GetMapping("/{id}")
	public Map<String, Object> getCodeById(@PathVariable("id") String id) {
		Result result = new Result();

		result.setData("dma_code", new CodeVO(id));
		result.setMsg(result.STATUS_SUCESS);
	
		return result.getResult();
	}
}
