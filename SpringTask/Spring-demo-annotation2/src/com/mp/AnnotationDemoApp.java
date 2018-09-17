package com.mp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {
	public static void main(String as[]) {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationcontex.xml");
		Coach coach=context.getBean("tannniscoachcom",Coach.class);
		//Coach coach2=context.getBean("tannisCoach",Coach.class);//this will work only when we dont provide name of bean
		// TODO Auto-generated method stub
		System.out.println(coach.getDailyWorkout());
		//System.out.println(coach2.getDailyWorkout());
		

		context.close();
	}

}
