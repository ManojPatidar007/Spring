package com.hello;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class controller implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String name=req.getParameter("name");
		String pass=req.getParameter("pass");
		Map map=new HashMap();
		if(name.isEmpty()||pass.isEmpty()){
			map.put("msg", "sorry.........please enter id & password");
		}
		else{
			map.put("msg", "hello........welcome \t"+name);
		}
		ModelAndView move=new ModelAndView("success",map);

		return move;
	}

}
