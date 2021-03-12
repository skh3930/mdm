package com.kyobobook.mdm.mvc.code.process.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyobobook.mdm.mvc.code.process.CodeProcess;
import com.kyobobook.mdm.mvc.code.service.CodeService;
import com.kyobobook.mdm.mvc.code.vo.CmnCodeMpng;
import com.kyobobook.mdm.mvc.code.vo.CodeDtl;
import com.kyobobook.mdm.mvc.code.vo.CodeDtlRlts;
import com.kyobobook.mdm.mvc.code.vo.CodeGrp;
import com.kyobobook.mdm.mvc.code.vo.CodeRlts;

/**
 * 공통코드 프로세스 로직
 * @author <a href="mailto:sahlee@kico.co.kr">이상하</a>
 * @since 2011. 5. 19.
 */
@Service
public class CodeProcessImpl implements CodeProcess {

    @Autowired
    private CodeService codeService;

    /* 공통코드 등록
     * @see com.kyobobook.kflow.mdm.codeproc.facade.CodeProcess#registerCodes(java.util.List, java.util.List, java.util.List, java.util.List)
     */
    @Override
    public List<CodeGrp> registerCodes(List<CodeGrp> codeGrps,
                                       List<CodeDtl> codeDtls,
                                       List<CodeRlts> codeRltss,
                                       List<CodeDtlRlts> codeDtlRltss) {

        List<CodeGrp> resultList = new ArrayList<CodeGrp>();

        //----------------------------------------------------------------
        CodeGrp codeGrp = null; //코드그룹
        CodeDtl codeDtl = null; //코드상세
        CodeRlts codeRlts = null; //코드관계
        CodeDtlRlts codeDtlRlts = null; //코드상세관계
        //----------------------------------------------------------------

        for (int i=0; i<codeGrps.size(); i++) {

            codeGrp = codeGrps.get(i);

            if (codeDtls != null && i < codeDtls.size()) {
                codeDtl = codeDtls.get(i);
            }
            if (codeRltss != null && i < codeRltss.size()) {
                codeRlts = codeRltss.get(i);
            }
            if (codeDtlRltss != null && i < codeDtlRltss.size()) {
                codeDtlRlts = codeDtlRltss.get(i);
            }

            //문화서비스상품 정보  등록
            resultList.add(codeService.registerCode(codeGrp, codeDtl, codeRlts, codeDtlRlts));

        }

        return resultList;

    }


    /* 공통코드 수정
     * @see com.kyobobook.kflow.mdm.codeproc.facade.CodeProcess#modifyCode(com.kyobobook.kflow.mdm.code.domain.CodeGrp, com.kyobobook.kflow.mdm.code.domain.CodeDtl, com.kyobobook.kflow.mdm.code.domain.CodeRlts, com.kyobobook.kflow.mdm.code.domain.CodeDtlRlts)
     */
    @Override
    public boolean modifyCode(CodeGrp codeGrp, CodeDtl codeDtl, CodeRlts codeRlts) {

        boolean result = true;

        //코드그룹
        if (codeGrp != null && StringUtils.isNotEmpty(codeGrp.getCodeId())) {
            if (codeGrp.getDltYsno().equals("Y")) //삭제
                result = codeService.removeCodeGrp(codeGrp);
            else
                result = codeService.modifyCodeGrp(codeGrp);
        }

        //코드상세
        if (codeDtl != null && StringUtils.isNotEmpty(codeDtl.getCodeId()) &&
                StringUtils.isNotEmpty(codeDtl.getCodeWrth())) {
            if (codeDtl.getDltYsno().equals("Y")) //삭제
                result = codeService.removeCodeDtl(codeDtl);
            else
                result = codeService.modifyCodeDtl(codeDtl);
        }



        //코드관계
        if (codeRlts != null && StringUtils.isNotEmpty(codeRlts.getLwrnCodeId())) {

            //관계가 없으면서 기존에 상위코드ID가 있는 경우 상위 코드ID 관계 삭제
            //(기존에 상위코드ID가 있었는데 없음으로 수정한 경우)
            if (StringUtils.isEmpty(codeRlts.getCodeId()) &&
                    StringUtils.isNotEmpty(codeRlts.getOrgHgrnCodeId())) {

                codeRlts.setCodeId(codeRlts.getOrgHgrnCodeId());
                codeService.removeCodeRlts(codeRlts);

            }
            //관계가 있고 기존에 상위코드ID도 존재하는 경우 기존 상위코드ID 관계 삭제
            else if (StringUtils.isNotEmpty(codeRlts.getCodeId()) &&
                    StringUtils.isNotEmpty(codeRlts.getOrgHgrnCodeId())) {

                String codeId = codeRlts.getCodeId();
                String orgHgrnCodeId = codeRlts.getOrgHgrnCodeId();

                //기존관계 삭제
                if (!codeId.equals(orgHgrnCodeId)) {
                    codeRlts.setCodeId(codeRlts.getOrgHgrnCodeId());
                    codeService.removeCodeRlts(codeRlts);
                }

                codeRlts.setCodeId(codeId);
                codeRlts.setDltYsno("N");
                codeService.registerCodeRlts(codeRlts);

            }
            else if (StringUtils.isNotEmpty(codeRlts.getCodeId()) &&
                    StringUtils.isEmpty(codeRlts.getOrgHgrnCodeId())) {

                codeService.registerCodeRlts(codeRlts);

            }
        }

        return result;
    }


    /* 공통코드맵핑 저장
     * @see com.kyobobook.kflow.mdm.codeproc.facade.CodeProcess#saveCmnCodeMpngs(java.util.List, java.util.List, java.util.List)
     */
    @Override
    public List<CmnCodeMpng> saveCmnCodeMpngs(List<CmnCodeMpng> registerCmnCodeMpngs,
                                              List<CmnCodeMpng> modifyCmnCodeMpngs,
                                              List<CmnCodeMpng> removeCmnCodeMpngs) {

        List<CmnCodeMpng> resultList = new ArrayList<CmnCodeMpng>();

        //삭제 맵핑
        if (removeCmnCodeMpngs != null && removeCmnCodeMpngs.size()>0) {
            for (CmnCodeMpng removeCmnCodeMpng : removeCmnCodeMpngs) {
                if (codeService.removeCmnCodeMpng(removeCmnCodeMpng))
                    resultList.add(removeCmnCodeMpng);
            }
        }

        //추가 맵핑
        if (registerCmnCodeMpngs != null && registerCmnCodeMpngs.size()>0) {
            for (CmnCodeMpng registerCmnCodeMpng : registerCmnCodeMpngs) {
                codeService.registerCmnCodeMpng(registerCmnCodeMpng);
                resultList.add(registerCmnCodeMpng);
            }
        }

        //수정 맵핑
        if (modifyCmnCodeMpngs != null && modifyCmnCodeMpngs.size()>0) {
            for (CmnCodeMpng modifyCmnCodeMpng : modifyCmnCodeMpngs) {
                if (codeService.modifyCmnCodeMpng(modifyCmnCodeMpng))
                    resultList.add(modifyCmnCodeMpng);
            }
        }

        return resultList;

    }


}
