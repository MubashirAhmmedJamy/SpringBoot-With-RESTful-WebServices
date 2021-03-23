package SpringBootRestController.filterring;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeBeanController {
	
	@RequestMapping(method=RequestMethod.GET, path="/filterring")
	public SomeBean retrieveSomeBean(){
		System.out.println("Filterring Working");
		return new SomeBean("Value1", "Value2", "Value3");
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/filterring-list")
	public List<SomeBean> retrieveListOfSomeBean(){
		return Arrays.asList(new SomeBean("Value1", "Value2", "Value3"), new SomeBean("Value12", "Value22", "Value32"));
	}
	
}
