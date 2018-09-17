package com.mp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	@RequestMapping("/showForm")
	public String showForm(){
		return "hello-form";
	}

	@RequestMapping("/processform")
	public String processForm(){
		return "process-form";
	}
}
