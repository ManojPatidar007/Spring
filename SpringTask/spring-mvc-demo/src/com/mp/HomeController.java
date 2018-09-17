package com.mp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String shoePage(){
		
		return "main-menu";//"main-menu" is return jsp file name
	}
	

}
