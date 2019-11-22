package com.mdg.spring.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue getSomeBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field1", "field2");
		
		FilterProvider filterProvider = new SimpleFilterProvider().
				addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mappings = new MappingJacksonValue(someBean);
		mappings.setFilters(filterProvider);
		return mappings;
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue getSomeBeans() {
		List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value1", "value2", "value3"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field2", "field3");
		
		FilterProvider filterProvider = new SimpleFilterProvider().
				addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mappings = new MappingJacksonValue(list);
		mappings.setFilters(filterProvider);

		return mappings;
	}
}
