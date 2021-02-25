package com.kyobo.mdm.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.kyobo.mdm.common.dao.MenuMapper;


@Service
public class MenuService  {

	@Resource
	private MenuMapper menuMapper;
	
	public List selectMenuList(String userId) {
		return menuMapper.selectMenuList(userId);
	}
}
