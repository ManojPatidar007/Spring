package com.mp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnoConfigDemo {
	public static void main(String as[]) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("SportConfig.class");
		//ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationcontex.xml");
		Coach coach=context.getBean("tannniscoachcom",Coach.class);
		//Coach coach2=context.getBean("tannisCoach",Coach.class);//this will work only when we dont provide name of bean
		// TODO Auto-generated method stub
		System.out.println(coach.getDailyWorkout());
		//System.out.println(coach2.getDailyWorkout());
		

		context.close();
	}


}
