package SpringBootRestController.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public String greet(@RequestHeader(name="Accept-Language",required=false) String localeString){
		System.out.println("Locale:" + localeString);
		Locale locale = new Locale(localeString);
		System.out.println(locale.toString());
		
		String message = messageSource.getMessage("good.morning.message", null, locale);
		return message;
	}
	
}
