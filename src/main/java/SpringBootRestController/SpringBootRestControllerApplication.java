package SpringBootRestController;

import java.util.Locale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;



@SpringBootApplication
public class SpringBootRestControllerApplication {

	public static void main(String[] args) {
		ApplicationContext application_context = SpringApplication.run(SpringBootRestControllerApplication.class, args);
		
		int i = 0;
		
		for (String beanName : application_context.getBeanDefinitionNames()) {
			System.out.println("Bean:"+ ++i +": "+beanName);
		}
	}
	
	
	@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.CANADA);
//		System.out.println("\n\ninitiating LocaleResolver: "+localeResolver+"\n\n\n\n");
		return localeResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
//		messageSource.setUseCodeAsDefaultMessage(true);
//		System.out.println("\n\ninitiating Message Source: "+messageSource+"\n\n\n\n");
		return messageSource;	
	}
}
