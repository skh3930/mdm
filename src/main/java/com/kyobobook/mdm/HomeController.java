package com.kyobobook.mdm;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kyobobook.mdm.common.util.PageURIUtil;

@Controller
public class HomeController {
	
	@RequestMapping("/websquare/*.do")
	public ModelAndView WebSquareIndex(Map param, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String jspUrl = request.getRequestURI().replaceFirst("/websquare/", "").replaceFirst(".do", "");
		HttpSession session = request.getSession();
		String movePage = request.getParameter("w2xPath");
		
		if (jspUrl == null || jspUrl.equals("/") || jspUrl.equals("")) {
			jspUrl = "websquare";
		}
		
//		 if(!userInfo.isLogined()){         
//			 movePage = PageURIUtil.getLoginPage();
//		 }else{ 
//			 movePage = PageURIUtil.getIndexPageURI();     
//		 }
		 
		movePage = PageURIUtil.getIndexPageURI();   
		
		mv.setViewName("websquare");
		mv.addObject("movePage", movePage);
		return mv; 
	}
}
