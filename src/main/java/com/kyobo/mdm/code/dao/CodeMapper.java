package com.kyobo.mdm.code.dao;

import java.util.List;

import com.kyobo.mdm.code.vo.CodeVO;

public interface CodeMapper {
	public List<CodeVO> getList() throws Exception;
}
