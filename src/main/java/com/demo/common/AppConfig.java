package com.demo.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.config")
public class AppConfig {

	private String keycloackServerUrl;
	private String keycloackRealm;
	private String keycloackClientId;
	private String keycloackUsername;
	private String keycloackPassword;
	
	public String getKeycloackServerUrl() {
		return keycloackServerUrl;
	}
	public void setKeycloackServerUrl(String keycloackServerUrl) {
		this.keycloackServerUrl = keycloackServerUrl;
	}
	public String getKeycloackRealm() {
		return keycloackRealm;
	}
	public void setKeycloackRealm(String keycloackRealm) {
		this.keycloackRealm = keycloackRealm;
	}
	public String getKeycloackClientId() {
		return keycloackClientId;
	}
	public void setKeycloackClientId(String keycloackClientId) {
		this.keycloackClientId = keycloackClientId;
	}
	public String getKeycloackUsername() {
		return keycloackUsername;
	}
	public void setKeycloackUsername(String keycloackUsername) {
		this.keycloackUsername = keycloackUsername;
	}
	public String getKeycloackPassword() {
		return keycloackPassword;
	}
	public void setKeycloackPassword(String keycloackPassword) {
		this.keycloackPassword = keycloackPassword;
	}
	
	
}
