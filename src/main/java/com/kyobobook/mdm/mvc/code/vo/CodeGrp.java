package com.kyobobook.mdm.mvc.code.vo;

import com.kyobobook.mdm.common.vo.Common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CodeGrp extends Common {

    /** 코드ID*/
    private String codeId;

    /** 코드명*/
    private String codeName;

    /** 코드설명*/
    private String codeDscr;

    /** 코드길이*/
    private int codeLngt;

    /** 테이블명*/
    private String tablName;

    /** 대표여부*/
    private String rprsYsno;

    /** 서브관계존재여부*/
    private String subRltsExsnYsno;

    /** 서브관계상위코드ID*/
    private String subRltsHgrnCodeId;

    /** 포함관계존재여부*/
    private String inclRltsExsnYsno;

    /** 포함관계상위코드ID*/
    private String inclRltsHgrnCodeId;

    /** 코드값*/
    private String codeWrth;

    /** 코드값명*/
    private String codeWrthName;

}
