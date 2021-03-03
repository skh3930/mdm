package com.kyobo.mdm.mvc.code.service;

import java.util.List;

import com.kyobo.mdm.mvc.code.vo.CmnCodeMpng;
import com.kyobo.mdm.mvc.code.vo.CodeDtl;
import com.kyobo.mdm.mvc.code.vo.CodeDtlRlts;
import com.kyobo.mdm.mvc.code.vo.CodeGrp;
import com.kyobo.mdm.mvc.code.vo.CodeRlts;
import com.kyobo.mdm.mvc.code.vo.CodeSearch;
import com.kyobo.mdm.mvc.code.vo.CodeSearchCond;

/**
 * 공통코드 조회 서비스
 * @author <a href="mailto:sahlee@kico.co.kr">이상하</a>
 * @since 2011. 5. 20.
 */
public interface CodeSearchService {

    /**
     * 코드그룹 조회(검색)
     * @param codeSearchCond
     * @return
     */
    public List<CodeSearch> findCodes(CodeSearchCond codeSearchCond);

    /**
     * 코드그룹 조회
     * @param codeGrp
     * @return
     */
    public List<CodeGrp> readCodeGrp(CodeGrp codeGrp);

    /**
     * 코드상세 조회
     * @return
     */
    public List<CodeDtl> readCodeDtl(CodeDtl codeDtl);

    /**
     * 코드관계 조회
     * @param codeRlts
     * @return
     */
    public List<CodeRlts> readCodeRlts(CodeRlts codeRlts);

    /**
     * 코드상세관계 조회
     * @param codeDtlRlts
     * @return
     */
    public List<CodeDtlRlts> readCodeDtlRlts(CodeDtlRlts codeDtlRlts);

    /**
     * 공통코드맵핑 조회
     * @param cmnCodeMpng
     * @return
     */
    public List<CmnCodeMpng> readCmnCodeMpng(CmnCodeMpng cmnCodeMpng);

    /**
     * 코드ID의 코드값 조회
     * @return
     */
    public List<CodeDtl> readCodeDtl(String codeId);
}
