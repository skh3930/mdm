package com.kyobo.mdm.mvc.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyobo.mdm.mvc.code.dao.CodeSearchDao;
import com.kyobo.mdm.mvc.code.service.CodeSearchService;
import com.kyobo.mdm.mvc.code.vo.CmnCodeMpng;
import com.kyobo.mdm.mvc.code.vo.CodeDtl;
import com.kyobo.mdm.mvc.code.vo.CodeDtlRlts;
import com.kyobo.mdm.mvc.code.vo.CodeGrp;
import com.kyobo.mdm.mvc.code.vo.CodeRlts;
import com.kyobo.mdm.mvc.code.vo.CodeSearch;
import com.kyobo.mdm.mvc.code.vo.CodeSearchCond;

/**
 * 공통코드 조회 서비스 로직
 * @author <a href="mailto:sahlee@kico.co.kr">이상하</a>
 * @since 2011. 5. 20.
 */
@Service
public class CodeSearchServiceImpl implements CodeSearchService {

    @Autowired
    private CodeSearchDao codeSearchDao;

    /* 공통코드 조회(검색)
     * @see com.kyobobook.kflow.mdm.code.service.CodeSearchService#findCode(com.kyobobook.kflow.mdm.code.domain.CodeSearchCond)
     */
    @Override
    public List<CodeSearch> findCodes(CodeSearchCond codeSearchCond) {
        List<CodeSearch> codeSearch = codeSearchDao.findCodes(codeSearchCond);
        return codeSearch;
    }


    /* 코드그룹 조회
     * @see com.kyobobook.kflow.mdm.code.service.CodeSearchService#readCodeDtl(java.lang.String)
     */
    @Override
    public List<CodeGrp> readCodeGrp(CodeGrp codeGrp) {
        List<CodeGrp> codeGrpList = codeSearchDao.readCodeGrp(codeGrp);
        return codeGrpList;
    }

    /* 코드상세 조회
     * @see com.kyobobook.kflow.mdm.code.service.CodeSearchService#readCodeDtl(java.lang.String)
     */
    @Override
    public List<CodeDtl> readCodeDtl(CodeDtl codeDtl) {
        List<CodeDtl> codeDtlList = codeSearchDao.readCodeDtl(codeDtl);
        return codeDtlList;
    }

    /* 코드관계 조회
     * @see com.kyobobook.kflow.mdm.code.service.CodeSearchService#readCodeDtl(java.lang.String)
     */
    @Override
    public List<CodeRlts> readCodeRlts(CodeRlts codeRlts) {
        List<CodeRlts> codeRltsList = codeSearchDao.readCodeRlts(codeRlts);
        return codeRltsList;
    }

    /* 코드상세관계 조회
     * @see com.kyobobook.kflow.mdm.code.service.CodeSearchService#readCodeDtl(java.lang.String)
     */
    @Override
    public List<CodeDtlRlts> readCodeDtlRlts(CodeDtlRlts codeDtlRlts) {
        List<CodeDtlRlts> codeDtlRltsList = codeSearchDao.readCodeDtlRlts(codeDtlRlts);
        return codeDtlRltsList;
    }

    /* 공통코드맵핑 조회
     * @see com.kyobobook.kflow.mdm.code.service.CodeSearchService#readCodeDtl(java.lang.String)
     */
    @Override
    public List<CmnCodeMpng> readCmnCodeMpng(CmnCodeMpng cmnCodeMpng) {
        List<CmnCodeMpng> cmnCodeMpngList = codeSearchDao.readCmnCodeMpng(cmnCodeMpng);
        return cmnCodeMpngList;
    }

    /* 코드ID의 코드값들 조회
     * @see com.kyobobook.kflow.mdm.code.service.CodeSearchService#readCodeDtl(java.lang.String)
     */
    @Override
    public List<CodeDtl> readCodeDtl(String codeId) {
        List<CodeDtl> codeDtlList = codeSearchDao.readCodeDtl(codeId);
        return codeDtlList;
    }


}
