package com.kyobobook.mdm.mvc.code.vo;

import com.kyobobook.mdm.common.vo.Common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CodeDtl extends Common {

    /** 코드ID*/
    private String codeId;

    /** 코드값*/
    private String codeWrth;

    /** 코드값명*/
    private String codeWrthName;

    /** 코드값설명*/
    private String codeWrthDscr;

    /** 우선순위*/
    private int prrtRnkn;

    /** 코드값+코드값명*/
    private String codeWrthNameView;

}
