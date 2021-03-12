package com.kyobobook.mdm.mvc.code.vo;

import com.kyobobook.mdm.common.vo.Common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CmnCodeMpng extends Common {

    /** 공통코드매핑순번*/
    private int cmnCodeMpngSrmb;

    /** 코드ID*/
    private String codeId;

    /** 코드값*/
    private String codeWrth;

    /** 레거시시스템구분코드*/
    private String lgcySysDvsnCode;

    /** 레거시코드ID*/
    private String lgcyCodeId;

    /** 레거시코드값*/
    private String lgcyCodeWrth;

    /** 레거시코드테이블명*/
    private String lgcyCodeTablName;

}
