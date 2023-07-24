package com.demo.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt.auth.converter")
public class JwtAuthConverterProperties {

	private String resourceId;
	private String principalAttribue;

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getPrincipalAttribue() {
		return principalAttribue;
	}

	public void setPrincipalAttribue(String principalAttribue) {
		this.principalAttribue = principalAttribue;
	}
}
