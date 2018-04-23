package com.sample.api.configuration;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoggingDispatcherServlet extends DispatcherServlet {

    private final Logger logger = Logger.getLogger(LoggingDispatcherServlet.class);
    
    public LoggingDispatcherServlet(WebApplicationContext webApplicationContext) {
		super(webApplicationContext);
	}

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }
        HandlerExecutionChain handler = getHandler(request);

        try {
            super.doDispatch(request, response);
        } finally {
            log(request, response, handler);
            updateResponse(response);
        }
    }

    private void log(HttpServletRequest requestToCache, HttpServletResponse responseToCache, HandlerExecutionChain handler) throws JsonProcessingException, IOException {
        LogMessage logMessage = new LogMessage();
        logMessage.setHttpStatus(responseToCache.getStatus());
        logMessage.setHttpMethod(requestToCache.getMethod());
        logMessage.setPath(requestToCache.getRequestURI());
        logMessage.setClientIp(requestToCache.getRemoteAddr());
        if(null != handler){
        	logMessage.setJavaMethod(handler.toString());
        }
        logMessage.setRequestPayload(getRequestPayload(requestToCache));	
        String response = getResponsePayload(responseToCache);
        ObjectMapper mapper = new ObjectMapper();
        if(StringUtils.hasLength(response) && response.startsWith("{")){
        	Object json = mapper.readValue(response, Object.class);
        	logMessage.setResponse(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
        }        
        
        logger.info(logMessage);
    }

    private String getResponsePayload(HttpServletResponse response) {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {

            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                //int length = Math.min(buf.length, 5120);
            	int length = buf.length;
                try {
                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
                }
                catch (UnsupportedEncodingException ex) {
                    // NOOP
                }
            }
        }
        return "[unknown]";
    }
    
    private String getRequestPayload(HttpServletRequest request) {        
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {

            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                //int length = Math.min(buf.length, 5120);
            	int length = buf.length;
                try {
                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
                }
                catch (UnsupportedEncodingException ex) {
                    // NOOP
                }
            }
        }
        return "[unknown]";
    }

    private void updateResponse(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper responseWrapper =
            WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        responseWrapper.copyBodyToResponse();
    }
    
}
