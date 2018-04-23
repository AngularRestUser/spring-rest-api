package com.sample.api.configuration;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.sample.api.dao.ApplicationDAO;
import com.sample.api.model.KeyValueBean;

@Component
@Configuration
public class DataCachingService {
private static Logger logger = Logger.getLogger(DataCachingService.class);
	
	@Autowired
	ApplicationDAO applicationDAO;

	@Cacheable(value = "states")
	public List<KeyValueBean> getStates() throws Exception{

		logger.debug("setting up states in cache!");
		List<KeyValueBean> states = null;
	    return states;
	}
}
