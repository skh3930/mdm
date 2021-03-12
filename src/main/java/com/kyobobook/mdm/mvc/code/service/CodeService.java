package com.kyobobook.mdm.mvc.code.service;

import com.kyobobook.mdm.mvc.code.vo.CmnCodeMpng;
import com.kyobobook.mdm.mvc.code.vo.CodeDtl;
import com.kyobobook.mdm.mvc.code.vo.CodeDtlRlts;
import com.kyobobook.mdm.mvc.code.vo.CodeGrp;
import com.kyobobook.mdm.mvc.code.vo.CodeRlts;

/**
 * 공통코드 서비스
 * @author <a href="mailto:sahlee@kico.co.kr">이상하</a>
 * @since 2011. 5. 19.
 */
public interface CodeService {

    /**
     * 공통코드 등록
     * @param codeGrp
     * @param codeDtl
     * @param codeRlts
     * @param codeDtlRlts
     * @return
     */
    public CodeGrp registerCode(CodeGrp codeGrp, CodeDtl codeDtl, CodeRlts codeRlts, CodeDtlRlts codeDtlRlts);

    //-----------------------------------------------------------
    //코드그룹
    //-----------------------------------------------------------
    /**
     * 코드그룹 수정
     * @param codeGrp
     * @return
     */
    public boolean modifyCodeGrp(CodeGrp codeGrp);

    /**
     * 코드그룹 삭제
     * @param codeGrp
     * @return
     */
    public boolean removeCodeGrp(CodeGrp codeGrp);


    //-----------------------------------------------------------
    //코드상세
    //-----------------------------------------------------------
    /**
     * 코드상세 수정
     * @param codeDtl
     * @return
     */
    public boolean modifyCodeDtl(CodeDtl codeDtl);

    /**
     * 코드상세 삭제
     * @param codeDtl
     * @return
     */
    public boolean removeCodeDtl(CodeDtl codeDtl);


    //-----------------------------------------------------------
    //코드관계
    //-----------------------------------------------------------
    /**
     * 코드관계 수정
     * @param codeRlts
     * @return
     */
    public void registerCodeRlts(CodeRlts codeRlts);

    /**
     * 코드관계 수정
     * @param codeRlts
     * @return
     */
    public boolean modifyCodeRlts(CodeRlts codeRlts);

    /**
     * 코드관계 삭제
     * @param codeRlts
     * @return
     */
    public boolean removeCodeRlts(CodeRlts codeRlts);


    //-----------------------------------------------------------
    //코드상세관계
    //-----------------------------------------------------------
    /**
     * 코드상세관계 등록
     * @param codeDtlRlts
     * @return
     */
    public void registerCodeDtlRlts(CodeDtlRlts codeDtlRlts);

    /**
     * 코드상세관계 수정
     * @param codeDtlRlts
     * @return
     */
    public boolean modifyCodeDtlRlts(CodeDtlRlts codeDtlRlts);

    /**
     * 코드상세관계 삭제
     * @param codeDtlRlts
     * @return
     */
    public boolean removeCodeDtlRlts(CodeDtlRlts codeDtlRlts);


    //-----------------------------------------------------------
    //공통코드매핑
    //-----------------------------------------------------------
    /**
     * 공통코드매핑 등록
     * @param cmnCodeMpng
     * @return
     */
    public void registerCmnCodeMpng(CmnCodeMpng cmnCodeMpng);

    /**
     * 공통코드매핑 수정
     * @param cmnCodeMpng
     * @return
     */
    public boolean modifyCmnCodeMpng(CmnCodeMpng cmnCodeMpng);

    /**
     * 공통코드매핑 삭제
     * @param cmnCodeMpng
     * @return
     */
    public boolean removeCmnCodeMpng(CmnCodeMpng cmnCodeMpng);

}
