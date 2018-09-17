package test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import employee.Employee;

public class Test {
	public static void main(String args[]){
		Resource resource=new ClassPathResource("bean/Spring.xml");
		BeanFactory factory=new XmlBeanFactory(resource);
	Employee e=(Employee)factory.getBean("r");
	e.show();
	Employee s=(Employee)factory.getBean("t");
	s.show();
	}

}
