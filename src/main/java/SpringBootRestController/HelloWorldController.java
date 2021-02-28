package SpringBootRestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping(method=RequestMethod.GET, path="/hello-world-bean")
	public HelloWorldBean Hello(){
		return new HelloWorldBean("Hello World");
	}
	
}
