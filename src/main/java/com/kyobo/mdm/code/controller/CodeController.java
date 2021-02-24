package com.kyobo.mdm.code.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
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
			//���� ó��
			result.setMsg(result.STATUS_SUCESS, "���� ó���Ǿ����ϴ�.");
		} catch (Exception e) {
			result.setMsg(result.STATUS_ERROR, "ó�� ���� ������ �߻��Ͽ����ϴ�.", e);
			e.printStackTrace();
		}
		return result.getResult();
	}
	
	@GetMapping("/{id}")
	public Map<String, Object> getCodeById(@PathVariable("id") String id) {
		ResultVO result = new ResultVO();
		try {
			result.setData("dma_code", new CodeVO(id));
			
			result.setMsg(result.STATUS_SUCESS, "���� ó���Ǿ����ϴ�.");
		} catch (Exception e) {
			result.setMsg(result.STATUS_ERROR, "ó�� ���� ������ �߻��Ͽ����ϴ�.", e);
			e.printStackTrace();
		}
		return result.getResult();
	}
}
