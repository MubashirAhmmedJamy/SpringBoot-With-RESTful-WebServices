package SpringBootRestController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootRestControllerApplication {

	public static void main(String[] args) {
		ApplicationContext application_context = SpringApplication.run(SpringBootRestControllerApplication.class, args);
		int i = 0;
		for (String beanName : application_context.getBeanDefinitionNames()) {
			System.out.println("Bean:"+ ++i +": "+beanName);
		}
	}
}
