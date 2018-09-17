package com.mp;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Test {
	public static void main(String ar[]){
		
		Resource rs=new ClassPathResource("spring.xml");
		BeanFactory factory=new XmlBeanFactory(rs);
		//factory.getBean("client");//at first user request creating obj
	}
	

}
