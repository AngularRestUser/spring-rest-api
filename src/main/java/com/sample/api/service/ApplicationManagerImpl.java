package com.sample.api.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.api.configuration.*;
import com.sample.api.dao.*;
import com.sample.api.model.*;
import com.sample.api.util.*;

@Service("applicationManager")
public class ApplicationManagerImpl implements ApplicationManager{
	
	@Autowired
	ApplicationDAO applicationDAO;
	
	
	@Autowired
	FormUtil formUtil;
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	@Autowired
	ConfigProperties configProperties;
	
	@Autowired
	DataCachingService dataCachingService;
	
	private static Logger logger = Logger.getLogger(ApplicationManagerImpl.class);
	
	
}

