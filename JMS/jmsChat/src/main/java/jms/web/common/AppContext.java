package jms.web.common;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {

	private static ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
	
	public static <T extends Object> T getBean(String name, Class<T> type) {
		return  type.cast(applicationContext.getBean(name));
	}
	
}
