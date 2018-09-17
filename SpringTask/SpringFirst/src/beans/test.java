package beans;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class test {
public static void main(String as[]){
		
		System.out.println("hello");
	Resource resource=new ClassPathResource("test/Spring.xml");
	BeanFactory fct=new XmlBeanFactory(resource);
	fct.getBean("t");
	//fct.getBean("r");
	fct.getBean("t");
	
	}
	
}
