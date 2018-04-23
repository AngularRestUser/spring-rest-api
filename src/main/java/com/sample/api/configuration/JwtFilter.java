package com.sample.api.configuration;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.HasAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(value = "jwtFilter")
//@WebFilter(urlPatterns = { "/*" })
public class JwtFilter implements Filter {

    @Autowired
    private JwtService jwtService;

    @Override public void init(FilterConfig filterConfig) throws ServletException  {}
    
    @Override public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    	
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final String authHeaderVal = httpRequest.getHeader("HEADER_TOKEN");    
        final String authTokenParam = ((HttpServletRequest) request).getParameter("TOEKN_PARAM");
        String authToken = authHeaderVal;
        if(null != authTokenParam){
        	authToken = authTokenParam;
        }
        
         //chain.doFilter(httpRequest, httpResponse);
             
        }
}
