package com.kyobobook.mdm.mvc.code.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyobobook.mdm.common.exception.MDMException;
import com.kyobobook.mdm.mvc.code.dao.CodeDao;
import com.kyobobook.mdm.mvc.code.service.CodeSearchService;
import com.kyobobook.mdm.mvc.code.service.CodeService;
import com.kyobobook.mdm.mvc.code.vo.CmnCodeMpng;
import com.kyobobook.mdm.mvc.code.vo.CodeDtl;
import com.kyobobook.mdm.mvc.code.vo.CodeDtlRlts;
import com.kyobobook.mdm.mvc.code.vo.CodeGrp;
import com.kyobobook.mdm.mvc.code.vo.CodeRlts;


@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeDao codeDao;

    @Autowired
    private CodeSearchService codeSearchService;

    //@Autowired
    //private EaiParamsService eaiParamsService;


    /* 공통코드 등록
     * @see com.kyobobook.kflow.mdm.code.service.CodeService#registerCode(com.kyobobook.kflow.mdm.code.domain.CodeGrp, com.kyobobook.kflow.mdm.code.domain.CodeDtl, com.kyobobook.kflow.mdm.code.domain.CodeRlts, com.kyobobook.kflow.mdm.code.domain.CodeDtlRlts)
     */
    @Override
    public CodeGrp registerCode(CodeGrp codeGrp, CodeDtl codeDtl, CodeRlts codeRlts, CodeDtlRlts codeDtlRlts) {
    	try {
	        //코드그룹
	        if (codeGrp != null && StringUtils.isNotEmpty(codeGrp.getCodeId())) {
	
	            if (StringUtils.isEmpty(codeGrp.getCodeDscr()))
	                codeGrp.setCodeDscr(codeGrp.getCodeName());
	
	            //코드그룹등록
	            this.registerCodeGrp(codeGrp);
	
	            codeDtl.setCodeId(codeGrp.getCodeId()); //코드그룹ID 세팅
	            if (codeDtl != null && StringUtils.isNotEmpty(codeDtl.getCodeWrth())) {
	
	                if (StringUtils.isEmpty(codeDtl.getCodeWrthDscr()))
	                    codeDtl.setCodeWrthDscr(codeDtl.getCodeWrthName());
	
	                //코드상세등록
	                this.registerCodeDtl(codeDtl);
	
	                //리턴값에 세팅-----------------------------------------
	                codeGrp.setCodeWrth(codeDtl.getCodeWrth());
	                codeGrp.setCodeWrthName(codeDtl.getCodeWrthName());
	                //-----------------------------------------------------
	
	                //-------------------------------------------------------------
	                //코드관계등록 및 코드상세관계 등록
	                //-------------------------------------------------------------
	                if (codeRlts != null &&
	                        StringUtils.isNotEmpty(codeRlts.getCodeId()) &&
	                        StringUtils.isNotEmpty(codeRlts.getLwrnCodeId()) &&
	                        StringUtils.isNotEmpty(codeRlts.getCodeRltsPatrCode())) {
	
	                    this.registerCodeRlts(codeRlts);
	
	                    //상하위 관계 코드 상세관계 등록-------------------------------------------
	                    if (codeRlts.getCodeRltsPatrCode().equals("003")) {
	                        if (codeDtlRlts != null &&
	                                StringUtils.isNotEmpty(codeDtlRlts.getCodeId()) &&
	                                StringUtils.isNotEmpty(codeDtlRlts.getCodeWrth()) &&
	                                StringUtils.isNotEmpty(codeDtlRlts.getLwrnCodeId())&&
	                                StringUtils.isNotEmpty(codeDtlRlts.getLwrnCodeWrth())) {
	
	                                this.registerCodeDtlRlts(codeDtlRlts);
	                        }
	                    }
	                    //--------------------------------------------------------------------
	                }
	
	            }
	        }
	        
	        return codeGrp;
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		throw new MDMException("등록중오류가발생하였습니다.");
    	}
    }

    //-----------------------------------------------------------------
    //코드그룹
    //-----------------------------------------------------------------
    /**
     * 코드그룹(TC_CODE_GRP) 등록
     * @param codeGrp
     */
    private void registerCodeGrp(CodeGrp codeGrp) {
        codeGrp.setLoginUserId("admin");

        //삭제여부가 삭제로 되어 있는 경우 dlt_ysno = "N" 로 업데이트 함.
        codeGrp.setDltYsno("");
        if (codeSearchService.readCodeGrp(codeGrp).size() > 0) {
            codeGrp.setDltYsno("N");
            this.modifyCodeGrp(codeGrp);
        }
        else {
            codeDao.registerCodeGrp(codeGrp);

            //EAI호출------------------------------------------
//            EaiParams eaiParams = new EaiParams();
//            eaiParams.setTableName("VW_TC_CODE_GRP_01");
//            eaiParams.setKey1(codeGrp.getCodeId());
//            eaiParamsService.registerCommodityEai(eaiParams);
            //-------------------------------------------------
        }
    }

    /**
     * 코드그룹(TC_CODE_GRP) 수정
     * @param codeGrp
     */
    public boolean modifyCodeGrp(CodeGrp codeGrp) {
    	codeGrp.setLoginUserId("admin");

        boolean result = codeDao.modifyCodeGrp(codeGrp);

        if (result) {

            //EAI호출------------------------------------------
//            EaiParams eaiParams = new EaiParams();
//            eaiParams.setTableName("VW_TC_CODE_GRP_01");
//            eaiParams.setKey1(codeGrp.getCodeId());
//            eaiParamsService.modifyCommodityEai(eaiParams);
            //-------------------------------------------------
        }

        return result;
    }

    /**
     * 코드그룹(TC_CODE_GRP) 삭제
     * @param codeGrp
     */
    public boolean removeCodeGrp(CodeGrp codeGrp) {
    	codeGrp.setLoginUserId("admin");

        boolean result = codeDao.removeCodeGrp(codeGrp);

        if (result) {
            //EAI호출------------------------------------------
//            EaiParams eaiParams = new EaiParams();
//            eaiParams.setTableName("VW_TC_CODE_GRP_01");
//            eaiParams.setKey1(codeGrp.getCodeId());
//            eaiParamsService.modifyCommodityEai(eaiParams);
            //-------------------------------------------------
        }

        return result;
    }

    //-----------------------------------------------------------------
    //코드상세
    //-----------------------------------------------------------------
    /**
     * 코드상세(TC_CODE_DTL) 등록
     * @param codeDtl
     */
    private void registerCodeDtl(CodeDtl codeDtl) {
        codeDtl.setLoginUserId("admin");

        //삭제여부가 삭제로 되어 있는 경우 dlt_ysno = "N" 로 업데이트 함.
        codeDtl.setDltYsno("");
        if (codeSearchService.readCodeDtl(codeDtl).size() > 0) {
            codeDtl.setDltYsno("N");

            codeDao.modifyCodeDtl(codeDtl);

            //EAI호출------------------------------------------
//            EaiParams eaiParams = new EaiParams();
//            eaiParams.setTableName("VW_TC_CODE_DTL_01");
//            eaiParams.setKey1(codeDtl.getCodeId());
//            eaiParams.setKey2(codeDtl.getCodeWrth());
//            eaiParamsService.modifyCommodityEai(eaiParams);
            //-------------------------------------------------

        }
        else {
            codeDao.registerCodeDtl(codeDtl);

            //EAI호출------------------------------------------
//            EaiParams eaiParams = new EaiParams();
//            eaiParams.setTableName("VW_TC_CODE_DTL_01");
//            eaiParams.setKey1(codeDtl.getCodeId());
//            eaiParams.setKey2(codeDtl.getCodeWrth());
//            eaiParamsService.registerCommodityEai(eaiParams);
            //-------------------------------------------------
        }
    }

    /**
     * 코드상세(TC_CODE_DTL) 수정
     * @param codeDtl
     */
    public boolean modifyCodeDtl(CodeDtl codeDtl) {
        codeDtl.setLoginUserId("admin");

        boolean result = codeDao.modifyCodeDtl(codeDtl);

        //EAI호출------------------------------------------
//        EaiParams eaiParams = new EaiParams();
//        eaiParams.setTableName("VW_TC_CODE_DTL_01");
//        eaiParams.setKey1(codeDtl.getCodeId());
//        eaiParams.setKey2(codeDtl.getCodeWrth());
//        eaiParamsService.modifyCommodityEai(eaiParams);
        //-------------------------------------------------

        if (result) {

            //----------------------------------------------------------------------
            //포함관계에 있는 코드상세 수정
            //----------------------------------------------------------------------
            CodeRlts tempCodeRlts = new CodeRlts();

            //상위기준으로 하위에 있는 코드들
            tempCodeRlts.setCodeId(codeDtl.getCodeId());
            tempCodeRlts.setLwrnCodeId("");
            tempCodeRlts.setCodeRltsPatrCode("001");
            tempCodeRlts.setDltYsno("N");
            List<CodeRlts> codeRltsList = codeSearchService.readCodeRlts(tempCodeRlts);
            for (CodeRlts codeRlts : codeRltsList) {
                CodeDtl codeDtlUpdate = new CodeDtl();
                codeDtlUpdate.setCodeId(codeRlts.getLwrnCodeId());
                codeDtlUpdate.setCodeWrth(codeDtl.getCodeWrth());
                codeDtlUpdate.setCodeWrthName(codeDtl.getCodeWrthName());
                codeDtlUpdate.setDltYsno(codeDtl.getDltYsno());
                codeDtlUpdate.setLoginUserId("admin");

                codeDao.modifyCodeDtl(codeDtlUpdate);

                //EAI호출------------------------------------------
                //EaiParams eaiParams = new EaiParams();
                //eaiParams.setTableName("VW_TC_CODE_DTL_01");
//                eaiParams.setKey1(codeDtlUpdate.getCodeId());
//                eaiParams.setKey2(codeDtlUpdate.getCodeWrth());
//                eaiParamsService.modifyCommodityEai(eaiParams);
                //-------------------------------------------------

            }

            //-------------------------------------------------------------
            //코드상세관계  변경
            //-------------------------------------------------------------
            for (CodeRlts codeRlts : codeRltsList) {

                CodeDtlRlts codeDtlRlts = new CodeDtlRlts();
                codeDtlRlts.setCodeId(codeRlts.getCodeId());
                codeDtlRlts.setCodeWrth(codeDtl.getCodeWrth());
                codeDtlRlts.setLwrnCodeId(codeRlts.getLwrnCodeId());
                codeDtlRlts.setLwrnCodeWrth(codeDtl.getCodeWrth());
                codeDtlRlts.setDltYsno(codeDtl.getDltYsno());
                this.modifyCodeDtlRlts(codeDtlRlts); //등록 또는 다시 살림

                codeDtlRlts = new CodeDtlRlts();
                codeDtlRlts.setLwrnCodeId(codeDtl.getCodeId());
                codeDtlRlts.setLwrnCodeWrth(codeDtl.getCodeWrth());
                codeDtlRlts.setDltYsno(codeDtl.getDltYsno());
                this.modifyCodeDtlRlts(codeDtlRlts); //등록 또는 다시 살림
            }
            //-------------------------------------------------------------


            //하위기준으로 상위에 있는 코드들
            tempCodeRlts.setCodeId("");
            tempCodeRlts.setLwrnCodeId(codeDtl.getCodeId());
            tempCodeRlts.setCodeRltsPatrCode("001");
            tempCodeRlts.setDltYsno("N");
            codeRltsList = codeSearchService.readCodeRlts(tempCodeRlts);
            for (CodeRlts codeRlts : codeRltsList) {
                CodeDtl codeDtlUpdate = new CodeDtl();
                codeDtlUpdate.setCodeId(codeRlts.getCodeId());
                codeDtlUpdate.setCodeWrth(codeDtl.getCodeWrth());
                codeDtlUpdate.setCodeWrthName(codeDtl.getCodeWrthName());
                codeDtlUpdate.setDltYsno(""); //상위는 삭제여부 적용 안함.
                codeDtlUpdate.setLoginUserId("admin");

                codeDao.modifyCodeDtl(codeDtlUpdate);

                //EAI호출------------------------------------------
                //EaiParams eaiParams = new EaiParams();
                //eaiParams.setTableName("VW_TC_CODE_DTL_01");
//                eaiParams.setKey1(codeDtlUpdate.getCodeId());
//                eaiParams.setKey2(codeDtlUpdate.getCodeWrth());
//                eaiParamsService.modifyCommodityEai(eaiParams);
                //-------------------------------------------------

            }
            //-----------------------------------------------------------------


            //-------------------------------------------------------------
            //코드상세관계  변경
            //-------------------------------------------------------------
            for (CodeRlts codeRlts : codeRltsList) {

                CodeDtlRlts codeDtlRlts = new CodeDtlRlts();
                codeDtlRlts.setCodeId(codeRlts.getCodeId());
                codeDtlRlts.setCodeWrth(codeDtl.getCodeWrth());
                codeDtlRlts.setLwrnCodeId(codeRlts.getLwrnCodeId());
                codeDtlRlts.setLwrnCodeWrth(codeDtl.getCodeWrth());
                codeDtlRlts.setDltYsno(codeDtl.getDltYsno());
                this.modifyCodeDtlRlts(codeDtlRlts); //등록 또는 다시 살림

                codeDtlRlts = new CodeDtlRlts();
                codeDtlRlts.setLwrnCodeId(codeDtl.getCodeId());
                codeDtlRlts.setLwrnCodeWrth(codeDtl.getCodeWrth());
                codeDtlRlts.setDltYsno(codeDtl.getDltYsno());
                this.modifyCodeDtlRlts(codeDtlRlts); //등록 또는 다시 살림
            }
            //-------------------------------------------------------------
        }

        return result;

    }

    /**
     * 코드상세(TC_CODE_DTL) 삭제
     * @param codeDtl
     */
    public boolean removeCodeDtl(CodeDtl codeDtl) {
        codeDtl.setLoginUserId("admin");

        boolean result = codeDao.removeCodeDtl(codeDtl);

        if (result) {
            //EAI호출------------------------------------------
//            EaiParams eaiParams = new EaiParams();
//            eaiParams.setTableName("VW_TC_CODE_DTL_01");
//            eaiParams.setKey1(codeDtl.getCodeId());
//            eaiParams.setKey2(codeDtl.getCodeWrth());
//            eaiParamsService.modifyCommodityEai(eaiParams);
            //-------------------------------------------------
        }

        if (result) {

            //----------------------------------------------------------------------
            //포함관계에 있는 코드상세도 삭제함.
            //----------------------------------------------------------------------
            CodeRlts tempCodeRlts = new CodeRlts();

            //상위기준으로 하위에 있는 코드들
            tempCodeRlts.setCodeId(codeDtl.getCodeId());
            tempCodeRlts.setCodeRltsPatrCode("001");
            tempCodeRlts.setDltYsno("N");
            List<CodeRlts> codeRltsList = codeSearchService.readCodeRlts(tempCodeRlts);
            for (CodeRlts codeRlts : codeRltsList) {
                CodeDtl codeDtlUpdate = new CodeDtl();
                codeDtlUpdate.setCodeId(codeRlts.getLwrnCodeId());
                codeDtlUpdate.setCodeWrth(codeDtl.getCodeWrth());
                this.removeCodeDtl(codeDtlUpdate);
            }
            //-----------------------------------------------------------------

            //-------------------------------------------------------------
            //코드상세관계 삭제
            //-------------------------------------------------------------
            CodeDtlRlts codeDtlRlts = new CodeDtlRlts();
            codeDtlRlts.setCodeId(codeDtl.getCodeId());
            codeDtlRlts.setCodeWrth(codeDtl.getCodeWrth());
            this.removeCodeDtlRlts(codeDtlRlts);

            codeDtlRlts = new CodeDtlRlts();
            codeDtlRlts.setLwrnCodeId(codeDtl.getCodeId());
            codeDtlRlts.setLwrnCodeWrth(codeDtl.getCodeWrth());
            this.removeCodeDtlRlts(codeDtlRlts);
            //-------------------------------------------------------------



        }

        return result;

    }

    //-----------------------------------------------------------------
    //코드관계
    //-----------------------------------------------------------------
    /**
     * 코드관계(TD_CODE_RLTS) 등록
     * @param codeRlts
     */
    public void registerCodeRlts(CodeRlts codeRlts) {
    	
        codeRlts.setLoginUserId("admin");

        //삭제여부가 삭제로 되어 있는 경우 dlt_ysno = "N" 로 업데이트 함.
        codeRlts.setDltYsno("");
        if (codeSearchService.readCodeRlts(codeRlts).size() > 0) {
            codeRlts.setDltYsno("N");

            codeDao.modifyCodeRlts(codeRlts);
            //boolean result = codeDao.modifyCodeRlts(codeRlts);

            /*if (result) {
                //EAI호출------------------------------------------
                EaiParams eaiParams = new EaiParams();
                eaiParams.setTableName("TD_CODE_RLTS");
                eaiParams.setKey1(codeRlts.getCodeId());
                eaiParams.setKey2(codeRlts.getLwrnCodeId());
                eaiParamsService.modifyCommodityEai(eaiParams);
                //-------------------------------------------------
            }*/
        }
        else {
            codeDao.registerCodeRlts(codeRlts);

            /*//EAI호출------------------------------------------
            EaiParams eaiParams = new EaiParams();
            eaiParams.setTableName("TD_CODE_RLTS");
            eaiParams.setKey1(codeRlts.getCodeId());
            eaiParams.setKey2(codeRlts.getLwrnCodeId());
            eaiParamsService.registerCommodityEai(eaiParams);
            //-------------------------------------------------*/
        }

        //--------------------------------------------------------------------
        //상위코드ID 그룹정보 수정
        //--------------------------------------------------------------------
        CodeGrp codeGrp = new CodeGrp();
        codeGrp.setCodeId(codeRlts.getCodeId()); //상위코드ID
        codeGrp.setRprsYsno("Y"); //대표여부
        if (codeRlts.getCodeRltsPatrCode().equals("001")) { //포함관계
            codeGrp.setInclRltsExsnYsno("Y");
        }
        else if (codeRlts.getCodeRltsPatrCode().equals("002")) { //서브관계
            codeGrp.setSubRltsExsnYsno("Y");
        }
        else if (codeRlts.getCodeRltsPatrCode().equals("003")) { //상하위관계
            codeGrp.setInclRltsExsnYsno("N");
            codeGrp.setSubRltsExsnYsno("N");
        }


        this.modifyCodeGrp(codeGrp);
        //-------------------------------------------------------------------

        //--------------------------------------------------------------------
        //하위코드ID 그룹정보 수정
        //--------------------------------------------------------------------
        codeGrp.setCodeId(codeRlts.getLwrnCodeId()); //하위코드ID
        codeGrp.setRprsYsno("N");
        if (codeRlts.getCodeRltsPatrCode().equals("001")) { //포함관계
            codeGrp.setInclRltsExsnYsno("Y");
            codeGrp.setSubRltsExsnYsno("N");
        }
        else if (codeRlts.getCodeRltsPatrCode().equals("002")) { //서브관계
            codeGrp.setInclRltsExsnYsno("N");
            codeGrp.setSubRltsExsnYsno("Y");
        }
        else if (codeRlts.getCodeRltsPatrCode().equals("003")) { //서브관계
            codeGrp.setInclRltsExsnYsno("N");
            codeGrp.setSubRltsExsnYsno("N");
        }

        this.modifyCodeGrp(codeGrp);
        //-------------------------------------------------------------------


        //--------------------------------------------------------------------
        //연관상세관계(TD_CODE_DTL_RLTS) 정보 등록
        //--------------------------------------------------------------------
        if (codeRlts.getCodeRltsPatrCode().equals("001")) { //포함관계
            List<CodeDtl> codeDtls = codeSearchService.readCodeDtl(codeRlts.getLwrnCodeId());
            CodeDtlRlts codeDtlRlts = new CodeDtlRlts();
            codeDtlRlts.setCodeId(codeRlts.getCodeId());
            for (CodeDtl codeDtl : codeDtls) {
                codeDtlRlts.setCodeWrth(codeDtl.getCodeWrth());
                codeDtlRlts.setLwrnCodeId(codeDtl.getCodeId());
                codeDtlRlts.setLwrnCodeWrth(codeDtl.getCodeWrth());
                this.registerCodeDtlRlts(codeDtlRlts);
            }
        }
        //--------------------------------------------------------------------

    }

    /**
     * 코드관계(TD_CODE_RLTS) 수정
     * @param codeRlts
     */
    public boolean modifyCodeRlts(CodeRlts codeRlts) {
        codeRlts.setLoginUserId("admin");

        boolean result = codeDao.modifyCodeRlts(codeRlts);

        /*if (result) {
            //EAI호출------------------------------------------
            EaiParams eaiParams = new EaiParams();
            eaiParams.setTableName("TD_CODE_RLTS");
            eaiParams.setKey1(codeRlts.getCodeId());
            eaiParams.setKey2(codeRlts.getLwrnCodeId());
            eaiParamsService.modifyCommodityEai(eaiParams);
            //-------------------------------------------------
        }*/

        if (result) {

            //--------------------------------------------------------------------
            //상위코드ID 그룹정보 수정
            //--------------------------------------------------------------------
            CodeGrp codeGrp = new CodeGrp();
            codeGrp.setCodeId(codeRlts.getCodeId()); //상위코드ID
            codeGrp.setRprsYsno("Y"); //대표여부
            if (codeRlts.getCodeRltsPatrCode().equals("001")) { //포함관계
                codeGrp.setInclRltsExsnYsno("Y");
            }
            else if (codeRlts.getCodeRltsPatrCode().equals("002")) { //서브관계
                codeGrp.setSubRltsExsnYsno("Y");
            }

            this.modifyCodeGrp(codeGrp);
            //------------------------------------------------------------------

            //------------------------------------------------------------------
            //하위코드ID 그룹정보 수정
            //------------------------------------------------------------------
            codeGrp.setCodeId(codeRlts.getLwrnCodeId()); //하위코드ID
            codeGrp.setRprsYsno("N");
            if (codeRlts.getCodeRltsPatrCode().equals("001")) { //포함관계
                codeGrp.setInclRltsExsnYsno("Y");
                codeGrp.setSubRltsExsnYsno("N");
            }
            else if (codeRlts.getCodeRltsPatrCode().equals("002")) { //서브관계
                codeGrp.setInclRltsExsnYsno("N");
                codeGrp.setSubRltsExsnYsno("Y");
            }

            this.modifyCodeGrp(codeGrp);
            //------------------------------------------------------------------

            //------------------------------------------------------------------
            //연관상세관계(TD_CODE_DTL_RLTS) 정보 등록
            //------------------------------------------------------------------
            if (codeRlts.getCodeRltsPatrCode().equals("001")) {
                List<CodeDtl> codeDtls = codeSearchService.readCodeDtl(codeRlts.getLwrnCodeId());
                CodeDtlRlts codeDtlRlts = new CodeDtlRlts();
                codeDtlRlts.setCodeId(codeRlts.getCodeId());
                for (CodeDtl codeDtl : codeDtls) {
                    codeDtlRlts.setCodeWrth(codeDtl.getCodeWrth());
                    codeDtlRlts.setLwrnCodeId(codeDtl.getCodeId());
                    codeDtlRlts.setLwrnCodeWrth(codeDtl.getCodeWrth());

                    if (codeRlts.getDltYsno().equals("N"))
                        this.registerCodeDtlRlts(codeDtlRlts);
                    else if (codeRlts.getDltYsno().equals("Y"))
                        this.removeCodeDtlRlts(codeDtlRlts);
                }
            }
            //------------------------------------------------------------------
        }

        return result;

    }

    /**
     * 코드관계(TD_CODE_RLTS) 삭제
     * @param codeRlts
     */
    public boolean removeCodeRlts(CodeRlts codeRlts) {
        codeRlts.setLoginUserId("admin");

        boolean result = codeDao.removeCodeRlts(codeRlts);

        /*if (result) {
            //EAI호출------------------------------------------
            EaiParams eaiParams = new EaiParams();
            eaiParams.setTableName("TD_CODE_RLTS");
            eaiParams.setKey1(codeRlts.getCodeId());
            eaiParams.setKey2(codeRlts.getLwrnCodeId());
            eaiParamsService.modifyCommodityEai(eaiParams);
            //-------------------------------------------------
        }*/

        if (result) {

            //------------------------------------------------------------------
            //상위코드ID 그룹정보세팅
            //------------------------------------------------------------------
            CodeGrp codeGrp = new CodeGrp();
            codeGrp.setCodeId(codeRlts.getCodeId()); //상위코드ID

            CodeRlts tempCodeRlts = new CodeRlts();
            tempCodeRlts.setCodeId(codeRlts.getCodeId());
            tempCodeRlts.setLwrnCodeId("");
            tempCodeRlts.setDltYsno("N");
            List<CodeRlts> existingCodeRltsList = codeSearchService.readCodeRlts(tempCodeRlts);

            codeGrp.setRprsYsno("N"); //대표여부
            codeGrp.setInclRltsExsnYsno("N");
            codeGrp.setSubRltsExsnYsno("N");

            if (existingCodeRltsList.size() > 0) { //연관관계가 있는 경우
                for (CodeRlts existingCodeRlts : existingCodeRltsList) {

                    if (existingCodeRlts.getCodeRltsPatrCode().equals("001")) {
                        codeGrp.setRprsYsno("Y"); //대표여부
                        codeGrp.setInclRltsExsnYsno("Y"); //포함관계여부
                    }
                    else if (existingCodeRlts.getCodeRltsPatrCode().equals("002")) {
                        codeGrp.setRprsYsno("Y"); //대표여부
                        codeGrp.setSubRltsExsnYsno("Y"); //서브관계여부
                    }
                }
            }


            //하위코드ID로 존재하는지도 체크
            tempCodeRlts.setCodeId("");
            tempCodeRlts.setLwrnCodeId(codeRlts.getCodeId()); //하위로 존재여부 체크
            tempCodeRlts.setDltYsno("N");
            existingCodeRltsList = codeSearchService.readCodeRlts(tempCodeRlts);

            if (existingCodeRltsList.size() > 0) { //연관관계가 있는 경우
                for (CodeRlts existingCodeRlts : existingCodeRltsList) {
                    if (existingCodeRlts.getCodeRltsPatrCode().equals("001")) {
                        codeGrp.setInclRltsExsnYsno("Y"); //포함관계여부
                    }
                    else if (existingCodeRlts.getCodeRltsPatrCode().equals("002")) {
                        codeGrp.setSubRltsExsnYsno("Y"); //서브관계여부
                    }
                }
            }

            this.modifyCodeGrp(codeGrp); //반영
            //-----------------------------------------------------------------


            //------------------------------------------------------------------
            //하위코드ID 그룹정보세팅
            //-----------------------------------------------------------------
            codeGrp.setCodeId(codeRlts.getLwrnCodeId()); //하위코드ID

            tempCodeRlts.setLwrnCodeId(codeRlts.getLwrnCodeId()); //하위로 존재여부 체크
            tempCodeRlts.setDltYsno("N");
            existingCodeRltsList = codeSearchService.readCodeRlts(tempCodeRlts);

            codeGrp.setRprsYsno("N"); //대표여부
            codeGrp.setInclRltsExsnYsno("N");
            codeGrp.setSubRltsExsnYsno("N");

            if (existingCodeRltsList.size() > 0) { //연관관계가 있는 경우
                for (CodeRlts existingCodeRlts : existingCodeRltsList) {
                    if (existingCodeRlts.getCodeRltsPatrCode().equals("001")) {
                        codeGrp.setInclRltsExsnYsno("Y"); //포함관계여부
                    }
                    else if (existingCodeRlts.getCodeRltsPatrCode().equals("002")) {
                        codeGrp.setSubRltsExsnYsno("Y"); //서브관계여부
                    }
                }
            }

            //상위코드ID로 존재하는지도 체크
            tempCodeRlts.setCodeId(codeRlts.getLwrnCodeId()); //상위로 존재여부 체크
            existingCodeRltsList = codeSearchService.readCodeRlts(tempCodeRlts);

            if (existingCodeRltsList.size() > 0) { //연관관계가 있는 경우
                for (CodeRlts existingCodeRlts : existingCodeRltsList) {
                    if (existingCodeRlts.getCodeRltsPatrCode().equals("001")) {
                        codeGrp.setRprsYsno("Y"); //대표여부
                        codeGrp.setInclRltsExsnYsno("Y"); //포함관계여부
                    }
                    else if (existingCodeRlts.getCodeRltsPatrCode().equals("002")) {
                        codeGrp.setRprsYsno("Y"); //대표여부
                        codeGrp.setSubRltsExsnYsno("Y"); //서브관계여부
                    }
                }
            }

            this.modifyCodeGrp(codeGrp); //반영
            //------------------------------------------------------------------

            //-------------------------------------------------------------
            //코드상세관계 삭제
            //-------------------------------------------------------------
            CodeDtlRlts codeDtlRlts = new CodeDtlRlts();
            codeDtlRlts.setCodeId(codeRlts.getCodeId());
            codeDtlRlts.setLwrnCodeId(codeRlts.getLwrnCodeId());
            if (StringUtils.isNotEmpty(codeDtlRlts.getCodeId()) &&
                    StringUtils.isNotEmpty(codeDtlRlts.getLwrnCodeId())) {
                this.removeCodeDtlRlts(codeDtlRlts);
            }
            //-------------------------------------------------------------

        }

        return result;

    }


    //-----------------------------------------------------------------
    //코드상세관계
    //-----------------------------------------------------------------
    /**
     * 코드상세관계(TD_CODE_DTL_RLTS) 등록
     * @param codeDtlRlts
     */
    public void registerCodeDtlRlts(CodeDtlRlts codeDtlRlts) {
        codeDtlRlts.setLoginUserId("admin");

        //삭제여부가 삭제로 되어 있는 경우 dlt_ysno = "N" 로 업데이트 함.
        codeDtlRlts.setDltYsno("");
        if (codeSearchService.readCodeDtlRlts(codeDtlRlts).size() > 0) {
            codeDtlRlts.setDltYsno("N");

            this.modifyCodeDtlRlts(codeDtlRlts);
        }
        else {
            codeDao.registerCodeDtlRlts(codeDtlRlts);

            /*//EAI호출------------------------------------------
            EaiParams eaiParams = new EaiParams();
            eaiParams.setTableName("TD_CODE_DTL_RLTS");
            eaiParams.setKey1(codeDtlRlts.getCodeId());
            eaiParams.setKey2(codeDtlRlts.getCodeWrth());
            eaiParams.setKey3(codeDtlRlts.getLwrnCodeId());
            eaiParams.setKey4(codeDtlRlts.getLwrnCodeWrth());
            eaiParamsService.registerCommodityEai(eaiParams);
            //-------------------------------------------------*/
        }
    }

    /**
     * 코드상세관계(TD_CODE_DTL_RLTS) 수정
     * @param cmnCodeMpng
     */
    public boolean modifyCodeDtlRlts(CodeDtlRlts codeDtlRlts) {
        codeDtlRlts.setLoginUserId("admin");

        boolean result = codeDao.modifyCodeDtlRlts(codeDtlRlts);

        return result;
    }

    /**
     * 코드상세관계(TD_CODE_DTL_RLTS) 삭제
     * @param codeDtlRlts
     */
    public boolean removeCodeDtlRlts(CodeDtlRlts codeDtlRlts) {
        codeDtlRlts.setLoginUserId("admin");

        boolean result = false;
        result = codeDao.removeCodeDtlRlts(codeDtlRlts);

        return result;
    }

    //-----------------------------------------------------------------
    //공통코드매핑
    //-----------------------------------------------------------------
    /**
     * 공통코드매핑(TC_CMN_CODE_MPNG) 등록
     * @param cmnCodeMpng
     */
    public void registerCmnCodeMpng(CmnCodeMpng cmnCodeMpng) {
        cmnCodeMpng.setLoginUserId("admin");
        codeDao.registerCmnCodeMpng(cmnCodeMpng);

        //EAI호출------------------------------------------
//        EaiParams eaiParams = new EaiParams();
//        eaiParams.setTableName("TC_CMN_CODE_MPNG");
//        eaiParams.setKey1(cmnCodeMpng.getCmnCodeMpngSrmb()+"");
//        eaiParamsService.registerCommodityEai(eaiParams);
        //-------------------------------------------------
    }

    /**
     * 공통코드매핑(TC_CMN_CODE_MPNG) 수정
     * @param cmnCodeMpng
     */
    public boolean modifyCmnCodeMpng(CmnCodeMpng cmnCodeMpng) {
        cmnCodeMpng.setLoginUserId("admin");

        boolean result = codeDao.modifyCmnCodeMpng(cmnCodeMpng);

        if (result) {
            //EAI호출------------------------------------------
//            EaiParams eaiParams = new EaiParams();
//            eaiParams.setTableName("TC_CMN_CODE_MPNG");
//            eaiParams.setKey1(cmnCodeMpng.getCmnCodeMpngSrmb()+"");
//            eaiParamsService.modifyCommodityEai(eaiParams);
            //-------------------------------------------------
        }

        return result;
    }

    /**
     * 공통코드매핑(TC_CMN_CODE_MPNG) 삭제
     * @param cmnCodeMpng
     */
    public boolean removeCmnCodeMpng(CmnCodeMpng cmnCodeMpng) {
        cmnCodeMpng.setLoginUserId("admin");

        boolean result = codeDao.removeCmnCodeMpng(cmnCodeMpng);

        if (result) {
            //EAI호출------------------------------------------
//            EaiParams eaiParams = new EaiParams();
//            eaiParams.setTableName("TC_CMN_CODE_MPNG");
//            eaiParams.setKey1(cmnCodeMpng.getCmnCodeMpngSrmb()+"");
//            eaiParamsService.modifyCommodityEai(eaiParams);
            //-------------------------------------------------
        }

        return result;
    }
}
