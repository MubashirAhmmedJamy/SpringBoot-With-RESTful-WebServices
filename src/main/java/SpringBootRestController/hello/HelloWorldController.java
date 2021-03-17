package SpringBootRestController.hello;

import java.util.Locale;

import org.hibernate.validator.spi.messageinterpolation.LocaleResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.net.SyslogOutputStream;
import net.bytebuddy.asm.Advice.Local;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method=RequestMethod.GET, path="/hello-world-bean")
	public HelloWorldBean Hello(){
		return new HelloWorldBean("Hello World");
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/hello-world-bean/path-variable/{name}")
	public HelloWorldBean Hello(@PathVariable String name){
		return new HelloWorldBean("Hello World: "+name);
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/greetings")
	public String greet(@RequestHeader(name="Accept-Language",required=false) String locale){
		System.out.println("Locale:" + locale);
		Locale local = new Locale(locale);
		System.out.println(local.toString());
		
		String message = messageSource.getMessage("good.morning.message", null, local);
		return message;
	}
	
}
