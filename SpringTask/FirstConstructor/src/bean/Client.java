package bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Client {
	public static void main(String as[]){


		System.out.print("hello");
		Resource resource=new ClassPathResource("test/spring.xml");
		BeanFactory factory= new XmlBeanFactory(resource);
		factory.getBean("r");
	}
}
