package com.kyobo.mdm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping(value="/")
	public String home() {
		return "redirect:/websquare/websquare.html?w2xPath=/cm/layout/layout.xml";
	}
}
