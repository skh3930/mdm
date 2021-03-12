package com.kyobobook.mdm.common.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Common {
	
    /** 생성자ID*/
    private String crtrId;

    /** 생성일시*/
    private String cretDttm;

    /** 수정자ID*/
    private String amnrId;

    /** 수정일시*/
    private String amndDttm;

	/** 삭제여부*/
    private String dltYsno = "N";
    
    /** 로그인 유저 아이디*/
    private String loginUserId;

}
