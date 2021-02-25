package com.kyobo.mdm.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface MenuMapper {
	
	public List<HashMap<String, Object>> selectMenuList(String userId);
}
