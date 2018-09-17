package com.mp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
	@RequestMapping("/form")
	public String showStudent(Model theModel){
		Student theStudent = new Student();

		// add student object to the model
		theModel.addAttribute("student", theStudent);
		return "student-farm";
	}
	@RequestMapping("/processForm")
	public String processStudent(@ModelAttribute("student") Student theStudent){
		return "process-farm";
	}

}
