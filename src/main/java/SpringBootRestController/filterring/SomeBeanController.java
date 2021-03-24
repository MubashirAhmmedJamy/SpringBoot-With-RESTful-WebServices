package SpringBootRestController.filterring;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class SomeBeanController {

	
	//Static Filtering Methods
	
	@RequestMapping(method = RequestMethod.GET, path = "/filterring")
	public SomeBean retrieveSomeBean() {
		System.out.println("Filterring Working");
		return new SomeBean("Value1", "Value2", "Value3");
	}

	@RequestMapping(method = RequestMethod.GET, path = "/filterring-list")
	public List<SomeBean> retrieveSomeBeanList() {
		return Arrays.asList(new SomeBean("Value1", "Value2", "Value3"), new SomeBean("Value12", "Value22", "Value32"));
	}

	
	
	//Dynamic Filtering Methods
	@RequestMapping(method = RequestMethod.GET, path = "/filterring-dynamic")
	public MappingJacksonValue retrieveSomeBeanDynamic() {
		System.out.println("DynamicFilterring for bean Working");

		SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");
		String properties[] = { "field1", "field2" };

		return getMapping(someBean, properties);
	}
	

	@RequestMapping(method = RequestMethod.GET, path = "/filterring-dynamic-list")
	public MappingJacksonValue retrieveSomeBeanDynamicList() {
		System.out.println("Dynamic Filterring for list working");

		List<SomeBean> list = Arrays.asList(new SomeBean("Value1", "Value2", "Value3"),
				new SomeBean("Value12", "Value22", "Value32"));
		String properties[] = { "field2" };

		return getMapping(list, properties);
	}

	private MappingJacksonValue getMapping(Object object, String[] properties) {

		System.out.println("Getting Mappring for: " + object.toString() + " | " + properties.getClass());

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(properties);
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBean Filter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(object);
		mapping.setFilters(filters);
		return mapping;
	}

	// private MappingJacksonValue getMapping(Object object, Set<String>
	// properties){
	// SimpleBeanPropertyFilter filter =
	// SimpleBeanPropertyFilter.filterOutAllExcept(properties);
	// FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBean
	// Filter", filter);
	// MappingJacksonValue mapping = new MappingJacksonValue(object);
	// mapping.setFilters(filters);
	// return mapping;
	// }
	
	
	// Redundant Code
	// SimpleBeanPropertyFilter filter =
	// SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
	// FilterProvider filters = new
	// SimpleFilterProvider().addFilter("SomeBean Filter", filter);
	// MappingJacksonValue mapping = new MappingJacksonValue(someBean);
	// mapping.setFilters(filters);
}
