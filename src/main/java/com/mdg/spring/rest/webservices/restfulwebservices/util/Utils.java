package com.mdg.spring.rest.webservices.restfulwebservices.util;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class Utils {
	public static MappingJacksonValue getMappings() {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field1", "field2");
		
		FilterProvider filterProvider = new SimpleFilterProvider().
				addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mappings = new MappingJacksonValue(null);
		mappings.setFilters(filterProvider);
		return mappings;
	}
}
