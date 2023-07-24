package com.demo.utills;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.stereotype.Component;

import com.demo.common.AppConfig;

@Component
public class KeycloakInstanceBuilder {

	public Keycloak getInstance(AppConfig appConfig) {
		return KeycloakBuilder.builder().serverUrl(appConfig.getKeycloackServerUrl())
				.realm(appConfig.getKeycloackRealm()).username(appConfig.getKeycloackUsername())
				.password(appConfig.getKeycloackPassword()).clientId(appConfig.getKeycloackClientId()).build();
	}
}
