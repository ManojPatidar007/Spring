package com.mp;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class Test {
	public static void main(String ar[]){
		Resource rs=new ClassPathResource("spring.xml");
		BeanFactory factory=new XmlBeanFactory(rs);
		//ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
		//factory.getBean("client");
	}
}
