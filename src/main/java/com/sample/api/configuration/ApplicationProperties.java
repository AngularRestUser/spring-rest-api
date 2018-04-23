package com.sample.api.configuration;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.util.StringUtils;

@Configuration
@PropertySources({
	@PropertySource("classpath:application.properties")
})
public class ApplicationProperties {
	
	@Value("${print.url}")
	private String printUrl;
	
	@Value("${help.url}")
	private String helpUrl;
	
	@Value("${attachment.url}")
	private String attachmentUrl;
	
	@Value("${f159.url}")
	private String f159Url;
	
	@Value("${f602.url}")
	private String f602Url;
	
	@Value("${licmgr.url}")
	private String licmgrUrl;
	
	@Value("${fee.help.url}")
	private String feeHelpUrl;
	
	@Value("${privacyact.url}")
	private String privacyactUrl;
		
	public String getPrintUrl(String serverUrl, String applId) {
		String tempUrl = printUrl;
		tempUrl = tempUrl.replaceAll("<applId>", applId);
		return serverUrl + tempUrl;
	}
	
	public String getHelpUrl() {
		return helpUrl;
	}
	
	public String getAttachmentUrl(String serverUrl, String source, String purpCode, String origPurpCode, String applId) {
		String tempAttachmentUrl = attachmentUrl;
		
		String attachmentSource = (("M".equalsIgnoreCase(source))?"I":"E");
		tempAttachmentUrl = tempAttachmentUrl.replaceAll("<source>", Objects.toString(attachmentSource, ""));
		tempAttachmentUrl = tempAttachmentUrl.replaceAll("<purpCode>", Objects.toString(purpCode, ""));
		tempAttachmentUrl = tempAttachmentUrl.replaceAll("<origPurpCode>", Objects.toString(origPurpCode, ""));
		tempAttachmentUrl = tempAttachmentUrl.replaceAll("<applID>", Objects.toString(applId, ""));
		return serverUrl + tempAttachmentUrl;
	}
	
	public String getForm159Url(String serverUrl) {
		return serverUrl + f159Url;
	}
	
	public String getForm602Url(String serverUrl) {
		return serverUrl + f602Url;
	}	
	
	public String getLicenseManagerUrl(String serverUrl, String frnKey) {
		String tempLicMgrUrl = licmgrUrl;
		tempLicMgrUrl = tempLicMgrUrl.replaceAll("<frnKey>", Objects.toString(frnKey, ""));
		return serverUrl + tempLicMgrUrl;
	}
	
	public String getFeeHelpUrl(String serverUrl) {
		return serverUrl + feeHelpUrl;
	}
	
	public String getprivacyactUrl(String serverUrl) {
		return serverUrl + privacyactUrl;
	}
}
