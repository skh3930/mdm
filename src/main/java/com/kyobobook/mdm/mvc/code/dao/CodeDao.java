package com.kyobobook.mdm.mvc.code.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kyobobook.mdm.common.exception.MDMException;
import com.kyobobook.mdm.mvc.code.vo.CmnCodeMpng;
import com.kyobobook.mdm.mvc.code.vo.CodeDtl;
import com.kyobobook.mdm.mvc.code.vo.CodeDtlRlts;
import com.kyobobook.mdm.mvc.code.vo.CodeGrp;
import com.kyobobook.mdm.mvc.code.vo.CodeRlts;

@Repository
public class CodeDao {

	@Autowired
	private SqlSessionTemplate sqlTemplate;

	private static final String NAMESPACE = "com.kyobobook.mdm.mvc.code.dao.CodeDao.";

	// -----------------------------------------------------------------------------------------
	// 코드그룹
	// -----------------------------------------------------------------------------------------
	/**
	 * 코드그룹(TC_CODE_GRP) 등록
	 * 
	 * @param codeGrp
	 */
	public void registerCodeGrp(CodeGrp codeGrp) {
		try {
			sqlTemplate.insert(NAMESPACE + "registerCodeGrp", codeGrp);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MDMException("BMD70103");
		}
	}

	/**
	 * 코드그룹(TC_CODE_GRP) 수정
	 * 
	 * @param codeGrp
	 * @return boolean
	 */
	public boolean modifyCodeGrp(CodeGrp codeGrp) {
		try {
			return sqlTemplate.update(NAMESPACE + "modifyCodeGrp", codeGrp) > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MDMException("BMD70104");
		}
	}

	/**
	 * 코드그룹(TC_CODE_GRP) 삭제
	 * 
	 * @param codeGrp
	 * @return boolean
	 */
	public boolean removeCodeGrp(CodeGrp codeGrp) {
		try {
			return sqlTemplate.delete(NAMESPACE + "removeCodeGrp", codeGrp) > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MDMException("BMD70105");
		}
	}

	// -----------------------------------------------------------------------------------------
	// 코드상세
	// -----------------------------------------------------------------------------------------
	/**
	 * 코드상세(TC_CODE_DTL) 등록
	 * 
	 * @param codeDtl
	 */
	public void registerCodeDtl(CodeDtl codeDtl) {
		try {
			sqlTemplate.insert(NAMESPACE + "registerCodeGrp", codeDtl);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MDMException("BMD70106");
		}
	}

	/**
	 * 코드상세(TC_CODE_DTL) 수정
	 * 
	 * @param codeDtl
	 * @return boolean
	 */
	public boolean modifyCodeDtl(CodeDtl codeDtl) {
		try {
			return sqlTemplate.update(NAMESPACE + "modifyCodeDtl", codeDtl) > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MDMException("BMD70107");
		}
	}

	/**
	 * 코드상세(TC_CODE_DTL) 삭제
	 * 
	 * @param codeDtl
	 * @return boolean
	 */
	public boolean removeCodeDtl(CodeDtl codeDtl) {
		try {
			return sqlTemplate.delete(NAMESPACE + "removeCodeDtl", codeDtl) > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MDMException("BMD70108");
		}
	}

	// -----------------------------------------------------------------------------------------
	// 코드관계
	// -----------------------------------------------------------------------------------------
	/**
	 * 코드관계(TD_CODE_RLTS) 등록
	 * 
	 * @param codeRlts
	 */
	public void registerCodeRlts(CodeRlts codeRlts) {
		try {
			sqlTemplate.insert(NAMESPACE + "registerCodeRlts", codeRlts);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MDMException("BMD70109");
		}
	}

	/**
	 * 코드관계(TD_CODE_RLTS) 수정
	 * 
	 * @param codeRlts
	 * @return boolean
	 */
	public boolean modifyCodeRlts(CodeRlts codeRlts) {
		try {
			return sqlTemplate.update(NAMESPACE + "modifyCodeRlts", codeRlts) > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MDMException("BMD70110");
		}
	}

	/**
	 * 코드관계(TD_CODE_RLTS) 삭제
	 * 
	 * @param codeRlts
	 * @return boolean
	 */
	public boolean removeCodeRlts(CodeRlts codeRlts) {
		try {
			return sqlTemplate.delete(NAMESPACE + "removeCodeRlts", codeRlts) > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MDMException("BMD70111");
		}
	}

	// -----------------------------------------------------------------------------------------
	// 코드상세관계
	// -----------------------------------------------------------------------------------------
	/**
	 * 코드상세관계(TD_CODE_DTL_RLTS) 등록
	 * 
	 * @param codeDtlRlts
	 */
	public void registerCodeDtlRlts(CodeDtlRlts codeDtlRlts) {
		try {
			sqlTemplate.insert(NAMESPACE + "registerCodeDtlRlts", codeDtlRlts);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MDMException("BMD70112");
		}
	}

	/**
	 * 코드상세관계(TD_CODE_DTL_RLTS) 수정
	 * 
	 * @param codeDtlRlts
	 * @return
	 */
	public boolean modifyCodeDtlRlts(CodeDtlRlts codeDtlRlts) {
		try {
			return sqlTemplate.update(NAMESPACE + "modifyCodeDtlRlts", codeDtlRlts) > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MDMException("BMD70113");
		}
	}

	/**
	 * 코드상세관계(TD_CODE_DTL_RLTS) 삭제
	 * 
	 * @param codeDtlRlts
	 * @return boolean
	 */
	public boolean removeCodeDtlRlts(CodeDtlRlts codeDtlRlts) {
		try {
			return sqlTemplate.delete(NAMESPACE + "removeCodeDtlRlts", codeDtlRlts) > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MDMException("BMD70114");
		}
	}

	// -----------------------------------------------------------------------------------------
	// 공통코드매핑
	// -----------------------------------------------------------------------------------------
	/**
	 * 공통코드매핑(TC_CMN_CODE_MPNG) 등록
	 * 
	 * @param cmnCodeMpng
	 */
	public void registerCmnCodeMpng(CmnCodeMpng cmnCodeMpng) {
		try {
			sqlTemplate.insert(NAMESPACE + "registerCmnCodeMpng", cmnCodeMpng);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MDMException("BMD70115");
		}
	}

	/**
	 * 공통코드매핑(TC_CMN_CODE_MPNG) 수정
	 * 
	 * @param cmnCodeMpng
	 * @return boolean
	 */
	public boolean modifyCmnCodeMpng(CmnCodeMpng cmnCodeMpng) {
		try {
			return sqlTemplate.update(NAMESPACE + "modifyCmnCodeMpng", cmnCodeMpng) > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MDMException("BMD70116");
		}
	}

	/**
	 * 공통코드매핑(TC_CMN_CODE_MPNG) 삭제
	 * 
	 * @param cmnCodeMpng
	 * @return boolean
	 */
	public boolean removeCmnCodeMpng(CmnCodeMpng cmnCodeMpng) {
		try {
			return sqlTemplate.delete(NAMESPACE + "removeCmnCodeMpng", cmnCodeMpng) > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MDMException("BMD70117");
		}
	}

}
