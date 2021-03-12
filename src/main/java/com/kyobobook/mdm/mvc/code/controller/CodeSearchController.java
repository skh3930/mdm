package com.kyobobook.mdm.mvc.code.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyobobook.mdm.common.contstants.MDMConstants;
import com.kyobobook.mdm.common.util.BeanUtil;
import com.kyobobook.mdm.common.vo.Result;
import com.kyobobook.mdm.mvc.code.service.CodeSearchService;
import com.kyobobook.mdm.mvc.code.vo.CmnCodeMpng;
import com.kyobobook.mdm.mvc.code.vo.CodeDtl;
import com.kyobobook.mdm.mvc.code.vo.CodeGrp;
import com.kyobobook.mdm.mvc.code.vo.CodeRlts;
import com.kyobobook.mdm.mvc.code.vo.CodeSearch;
import com.kyobobook.mdm.mvc.code.vo.CodeSearchCond;

@RestController
@RequestMapping("/code")
public class CodeSearchController {

    @Autowired
    private CodeSearchService codeSearchService;

    /**
     * 코드 조회
     * @param Map
     * @return Map
     */
    @PostMapping("/findCode")
    public Map<String, Object> findCode(@RequestBody Map<String, Object> param) {

        CodeSearchCond codeSearchCond = BeanUtil.mapToBean(param.get("dma_codeSearchCond"), CodeSearchCond.class);
        
        List<CodeSearch> codes = codeSearchService.findCodes(codeSearchCond);

        Result result = new Result();
		result.setData("dlt_codeSearch", codes);
		result.setMsg(MDMConstants.STATUS_SUCESS);

		return result.getResult();
    }


    /**
     * 코드그룹 조회
     * @param Map
     * @return Map
     */
    @PostMapping("/readCodeGrp")
    public Map<String, Object> readCodeGrp(@RequestBody Map<String, Object> param) {

        String codeId = (String) param.get("codeId");

        //코드그룹
        CodeGrp codeGrp = new CodeGrp();
        codeGrp.setCodeId(codeId);
        List<CodeGrp> codeGrpList = codeSearchService.readCodeGrp(codeGrp);

        //코드관계
        CodeRlts codeRlts = new CodeRlts();
        codeRlts.setLwrnCodeId(codeId);
        List<CodeRlts> codeRltsList = codeSearchService.readCodeRlts(codeRlts);

        //코드상세
        List<CodeDtl> codeDtlList = codeSearchService.readCodeDtl(codeId);
        
        Result result = new Result();
		result.setData("dlt_codeGrp", codeGrpList);
		result.setData("dlt_codeRlts", codeRltsList);
		result.setData("dlt_codeDtlExist", codeDtlList);
		result.setMsg(MDMConstants.STATUS_SUCESS);

		return result.getResult();
    }


    /**
     * 상위코드ID의 코드값 조회
     * @param Map
     * @return Map
     */
    @PostMapping("/readCodeDtl")
    public Map<String, Object> readCodeDtl(@RequestBody Map<String, Object> param) {

    	String codeId = (String) param.get("codeId");

        List<CodeDtl> codeDtlList = codeSearchService.readCodeDtl(codeId);

        Result result = new Result();
		result.setData("dlt_hgrnCodeDtl", codeDtlList);
		result.setMsg(MDMConstants.STATUS_SUCESS);

		return result.getResult();
    }


    /**
     * 코드 조회
     * @param request
     * @return
     */
    @PostMapping("/readCode")
    public Map<String, Object> readCode(@RequestBody Map<String, Object> param) {

        String codeId = (String) param.get("codeId");
        String codeWrth = (String) param.get("codeWrth");

        //코드그룹
        CodeGrp codeGrp = new CodeGrp();
        codeGrp.setCodeId(codeId);
        codeGrp.setDltYsno(""); //삭제인것도 조회
        List<CodeGrp> codeGrpList = codeSearchService.readCodeGrp(codeGrp);

        //코드상세
        CodeDtl codeDtl = new CodeDtl();
        codeDtl.setCodeId(codeId);
        codeDtl.setCodeWrth(codeWrth);
        codeDtl.setDltYsno(""); //삭제인것도 조회
        List<CodeDtl> codeDtlList = codeSearchService.readCodeDtl(codeDtl);

        //코드관계
        CodeRlts codeRlts = new CodeRlts();
        codeRlts.setLwrnCodeId(codeId);
        //codeRlts.setDltYsno(""); //삭제인것도 조회
        List<CodeRlts> codeRltsList = codeSearchService.readCodeRlts(codeRlts);

        //공통코드맵핑
        CmnCodeMpng cmnCodeMpng = new CmnCodeMpng();
        cmnCodeMpng.setCodeId(codeId);
        cmnCodeMpng.setCodeWrth(codeWrth);
        //cmnCodeMpng.setDltYsno(""); //삭제인것도 조회
        List<CmnCodeMpng> cmnCodeMpngList = codeSearchService.readCmnCodeMpng(cmnCodeMpng);
        
        Result result = new Result();
		result.setData("dlt_codeGrp", codeGrpList);
		result.setData("dlt_codeDtl", codeDtlList);
		result.setData("dlt_codeDtl", codeRltsList);
		result.setData("dlt_cmnCodeMpng", cmnCodeMpngList);
		result.setMsg(MDMConstants.STATUS_SUCESS);

		return result.getResult();
    }
}
