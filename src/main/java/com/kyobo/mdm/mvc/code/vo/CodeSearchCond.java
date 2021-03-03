package com.kyobo.mdm.mvc.code.vo;

import com.kyobo.mdm.common.vo.Common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CodeSearchCond extends Common {

    /** 코드ID*/
    private String codeId;

    /** 코드명*/
    private String codeName;

}
