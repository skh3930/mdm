package com.kyobobook.mdm.mvc.code.process;

import java.util.List;

import com.kyobobook.mdm.mvc.code.vo.CmnCodeMpng;
import com.kyobobook.mdm.mvc.code.vo.CodeDtl;
import com.kyobobook.mdm.mvc.code.vo.CodeDtlRlts;
import com.kyobobook.mdm.mvc.code.vo.CodeGrp;
import com.kyobobook.mdm.mvc.code.vo.CodeRlts;


/**
 * 공통코드 프로세스
 * @author <a href="mailto:sahlee@kico.co.kr">이상하</a>
 * @since 2011. 5. 19.
 */
public interface CodeProcess {

    /**
     * 코드 등록
     * @param codeGrps
     * @param codeDtls
     * @param codeRltss
     * @param codeDtlRltss
     * @return
     */
    public List<CodeGrp> registerCodes(List<CodeGrp> codeGrps,
                                       List<CodeDtl> codeDtls,
                                       List<CodeRlts> codeRltss,
                                       List<CodeDtlRlts> codeDtlRltss);

    /**
     * 코드수정
     * @param codeGrp
     * @param codeDtl
     * @param codeRlts
     * @param codeDtlRlts
     * @return
     */
    public boolean modifyCode(CodeGrp codeGrp,
                              CodeDtl codeDtl,
                              CodeRlts codeRlts);


    /**
     * 공통코드 맵핑 등록/수정/삭제
     * @param registerCmnCodeMpngs
     * @param modifyCmnCodeMpngs
     * @param removeCmnCodeMpngs
     */
    public List<CmnCodeMpng> saveCmnCodeMpngs(List<CmnCodeMpng> registerCmnCodeMpngs,
                                              List<CmnCodeMpng> modifyCmnCodeMpngs,
                                              List<CmnCodeMpng> removeCmnCodeMpngs);

}
