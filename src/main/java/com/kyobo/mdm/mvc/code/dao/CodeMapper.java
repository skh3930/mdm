package com.kyobo.mdm.mvc.code.dao;

import java.util.List;

import com.kyobo.mdm.mvc.code.vo.CodeVO;

public interface CodeMapper {
	public List<CodeVO> getList() throws Exception;
}
