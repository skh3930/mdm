package com.kyobobook.mdm.mvc.code.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kyobobook.mdm.mvc.code.vo.CmnCodeMpng;
import com.kyobobook.mdm.mvc.code.vo.CodeDtl;
import com.kyobobook.mdm.mvc.code.vo.CodeDtlRlts;
import com.kyobobook.mdm.mvc.code.vo.CodeGrp;
import com.kyobobook.mdm.mvc.code.vo.CodeRlts;
import com.kyobobook.mdm.mvc.code.vo.CodeSearch;
import com.kyobobook.mdm.mvc.code.vo.CodeSearchCond;

@Repository
public class CodeSearchDao {
	
	@Autowired
	private SqlSessionTemplate sqlTemplate;
	
	private static final String NAMESPACE = "com.kyobobook.mdm.mvc.code.dao.CodeSearchDao.";

    //-----------------------------------------------------------------------------------------
    // 공통코드 조회(검색)
    //-----------------------------------------------------------------------------------------
    /**
     * 공통코드 조회(검색)
     * @param codeSearchCond
     * @return
     */
    public List<CodeSearch> findCodes(CodeSearchCond codeSearchCond) {
    	return sqlTemplate.selectList(NAMESPACE + "findCodes", codeSearchCond);
    }

    //-----------------------------------------------------------------------------------------
    // 코드그룹 조회
    //-----------------------------------------------------------------------------------------
    /**
     * 코드그룹(TC_CODE_GRP) 조회
     * @param codeId
     */
    public List<CodeGrp> readCodeGrp(CodeGrp codeGrp) {
    	return sqlTemplate.selectList(NAMESPACE + "readCodeGrp", codeGrp);
    }
    
    //-----------------------------------------------------------------------------------------
    // 코드상세 조회
    //-----------------------------------------------------------------------------------------
    /**
     * 코드상세(TC_CODE_DTL) 조회
     * @param codeDtl
     */
    public List<CodeDtl> readCodeDtl(CodeDtl codeDtl) {
    	return sqlTemplate.selectList(NAMESPACE + "readCodeDtl", codeDtl);
    }

    //-----------------------------------------------------------------------------------------
    // 코드관계 조회
    //-----------------------------------------------------------------------------------------
    /**
     * 코드관계(TC_CODE_RLTS) 조회
     * @param codeRlts
     */
    public List<CodeRlts> readCodeRlts(CodeRlts codeRlts) {
    	return sqlTemplate.selectList(NAMESPACE + "readCodeRlts", codeRlts);
    }

    //-----------------------------------------------------------------------------------------
    // 코드상세관계 조회
    //-----------------------------------------------------------------------------------------
    /**
     * 코드상세관계(TC_CODE_RLTS) 조회
     * @param codeId
     */
    public List<CodeDtlRlts> readCodeDtlRlts(CodeDtlRlts codeDtlRlts) {
    	return sqlTemplate.selectList(NAMESPACE + "readCodeDtlRlts", codeDtlRlts);
    }

    //-----------------------------------------------------------------------------------------
    // 공통코드맵핑 조회
    //-----------------------------------------------------------------------------------------
    /**
     * 코드그룹(TC_CODE_DTL) 조회
     * @param codeId
     */
    public List<CmnCodeMpng> readCmnCodeMpng(CmnCodeMpng cmnCodeMpng) {
    	return sqlTemplate.selectList(NAMESPACE + "readCmnCodeMpng", cmnCodeMpng);
    }
    
    //-----------------------------------------------------------------------------------------
    // 코드ID에 해당하는 코드상세(코드값) 조회
    //-----------------------------------------------------------------------------------------
    /**
     * 코드그룹(TC_CODE_DTL) 조회
     * @param codeId
     */
    public List<CodeDtl> readCodeDtl(String codeId) {
    	return sqlTemplate.selectList(NAMESPACE + "readCodeDtl", codeId);
    }

}
