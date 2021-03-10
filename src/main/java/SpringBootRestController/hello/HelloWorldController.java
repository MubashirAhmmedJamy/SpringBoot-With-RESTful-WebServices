package SpringBootRestController.hello;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping(method=RequestMethod.GET, path="/hello-world-bean")
	public HelloWorldBean Hello(){
		return new HelloWorldBean("Hello World");
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/hello-world-bean/path-variable/{name}")
	public HelloWorldBean Hello(@PathVariable String name){
		return new HelloWorldBean("Hello World: "+name);
	}
	
}
