package SpringBootRestController.filterring;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import ch.qos.logback.core.net.SyslogOutputStream;

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
	
	@RequestMapping(method=RequestMethod.GET, path="/filterring-dynamic")
	public MappingJacksonValue retrieveSomeBeanDynamic(){
		
		System.out.println("DynamicFilterring Working");
		
		SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBean Filter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;
	}
	
	
	@RequestMapping(method=RequestMethod.GET, path="/filterring-dynamic-list")
	public MappingJacksonValue retrieveListOfSomeBeanDynamic(){
		
		List<SomeBean> list = Arrays.asList(new SomeBean("Value1", "Value2", "Value3"), new SomeBean("Value12", "Value22", "Value32"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBean Filter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}
	
	
	
	private MappingJacksonValue getMapping(SomeBean someBean, String [] consider){
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(consider);
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBean Filter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;
	}
	
}
