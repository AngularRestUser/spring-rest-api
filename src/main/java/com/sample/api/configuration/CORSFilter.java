package com.sample.api.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component(value = "corsFilter")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {
	
	@Autowired
	private Environment environment;

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		String env = environment.getProperty("spring.profiles.active");
		System.out.println("Filtering on...........................................................");
		HttpServletResponse response = (HttpServletResponse) res;
		if(env != null && (env.equals("local") || env.equals("dev"))){
			response.setHeader("Access-Control-Allow-Origin", "*");
		}		
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, content-type, Access-Control-Allow-Headers, Authorization, X-Requested-Wit, X-Auth-Token, x-auth-token");
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {}

	public void destroy() {}

}