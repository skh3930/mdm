package com.kyobo.mdm.mvc.code.vo;

import com.kyobo.mdm.common.vo.Common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CodeDtlRlts extends Common {

    /** 코드ID*/
    private String codeId;

    /** 코드값*/
    private String codeWrth;

    /** 하위코드ID*/
    private String lwrnCodeId;

    /** 하위코드값*/
    private String lwrnCodeWrth;

}
