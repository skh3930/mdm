package com.kyobo.mdm.code.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyobo.mdm.code.vo.CodeVO;
import com.kyobo.mdm.common.vo.ResultVO;

@RestController
@RequestMapping("/code")
public class CodeController {
	@PostMapping
	public Map<String, Object> saveCode(@RequestBody Map<String, Object> param) {
		ResultVO result = new ResultVO();
		try {
			Map code = (Map) param.get("dma_code");
			//저장 처리
			result.setMsg(result.STATUS_SUCESS, "정상 처리되었습니다.");
		} catch (Exception e) {
			result.setMsg(result.STATUS_ERROR, "처리 도중 오류가 발생하였습니다.", e);
			e.printStackTrace();
		}
		return result.getResult();
	}
	
	@GetMapping("/{id}")
	public Map<String, Object> getCodeById(@PathVariable("id") String id) {
		ResultVO result = new ResultVO();
		try {
			//code 받아와서 보낼 예정
			result.setData("dma_code", new CodeVO(id));
			
			result.setMsg(result.STATUS_SUCESS, "정상 처리되었습니다.");
		} catch (Exception e) {
			result.setMsg(result.STATUS_ERROR, "처리 도중 오류가 발생하였습니다.", e);
			e.printStackTrace();
		}
		return result.getResult();
	}
}
