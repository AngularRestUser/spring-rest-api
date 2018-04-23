package com.sample.api.configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogMessage {
	private int httpStatus;
	private String httpMethod;
	private String path;
	private String clientIp;
	private String javaMethod;
	private String requestPayload;
	private String response;
	public int getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getJavaMethod() {
		return javaMethod;
	}
	public void setJavaMethod(String javaMethod) {
		this.javaMethod = javaMethod;
	}
	public String getRequestPayload() {
		return requestPayload;
	}
	public void setRequestPayload(String requestPayload) {
		this.requestPayload = requestPayload;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		StringBuilder sbr = new StringBuilder();
		sbr.append("\n")
		.append("********* API Request Log Starts*************").append("\n")
		.append("dateTime=").append(dateFormat.format(new Date())).append("\n")
		.append("clientIp=").append(clientIp).append("\n")
		.append("path=").append(path).append("\n")
		.append("httpMethod=").append(httpMethod).append("\n")
		.append("requestPayload=").append(requestPayload).append("\n")
		.append("response=").append(response).append("\n")
		.append("********* API Request Log Ends *************")
		.append("\n");
		return sbr.toString();
	}
}
