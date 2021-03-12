package com.kyobobook.mdm.mvc.code.vo;

import com.kyobobook.mdm.common.vo.Common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CodeRlts extends Common {

    /** 코드ID*/
    private String codeId;

    /** 하위코드ID*/
    private String lwrnCodeId;

    /** 코드관계유형코드*/
    private String codeRltsPatrCode;

    /** 기존상위코드ID*/
    private String orgHgrnCodeId;

}
