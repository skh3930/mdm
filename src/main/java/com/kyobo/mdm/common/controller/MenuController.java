package com.kyobo.mdm.common.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyobo.mdm.common.service.MenuService;
import com.kyobo.mdm.common.vo.Result;


@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@GetMapping
	public Map<String, Object> getInitMainInfo() {
		Result result = new Result();
		try {
			System.out.println(menuService.selectMenuList("100001").toString());
			result.setData("dlt_menu", menuService.selectMenuList("100001"));
			result.setMsg(result.STATUS_SUCESS, "메뉴정보가 조회 되었습니다.");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.setMsg(result.STATUS_ERROR, null, ex);
		}

		return result.getResult();
	}

}
