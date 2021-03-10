package com.kyobo.mdm.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PageURIUtil {
	private static String TAB_INDEX;
	private static String WIN_INDEX;
	private static String LOGIN_PAGE;
	 
	@Value("${w5xml.main.tab}")
	private void setTAB_INDEX(String tab_index) {
		TAB_INDEX = tab_index;
	} 
	 
	@Value("${w5xml.login}")
	private void setLOGIN_PAGE(String login_page){
		LOGIN_PAGE = login_page;
	}

	public static String getIndexPageURI() {
    	String rsURI = TAB_INDEX;
    	return rsURI;
    } 
    
    public static String getLoginPage(){
    	return LOGIN_PAGE;
    } 

}
