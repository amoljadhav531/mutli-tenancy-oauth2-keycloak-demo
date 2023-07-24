package com.demo.utills;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.config.TenantConnectionProvider;

@Component
public class CommonUtills {

	@Autowired
	private TenantConnectionProvider tenantConnectionProvider;
	
	public void switchSchema(String schema) {
		tenantConnectionProvider.setSchemaSwitch(true);
		tenantConnectionProvider.setNewSchema(schema);
	}
	
	public void switchBackSchema() {
		tenantConnectionProvider.setSchemaSwitch(false);
		tenantConnectionProvider.setNewSchema(null);
	}
}
