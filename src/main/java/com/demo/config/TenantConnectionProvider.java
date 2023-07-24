package com.demo.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import com.demo.common.Constants;

@Configuration
public class TenantConnectionProvider implements MultiTenantConnectionProvider {

	private static final long serialVersionUID = 1L;
	
	private DataSource dataSource;
	private String newSchema;
	private Boolean schemaSwitch;
	
	@Value("${app.config.keycloakUsername}")
	private String superUser;
	
	public TenantConnectionProvider(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public boolean isUnwrappableAs(Class unwrapType) {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		return null;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		return dataSource.getConnection();
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connection.close();
	}

	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		final Connection connection = getAnyConnection();
		if(tenantIdentifier.equalsIgnoreCase("customers") || tenantIdentifier.equalsIgnoreCase(superUser)) {
			connection.createStatement().execute(String.format("SET SEARCH_PATH = \"%s\";", "customers"));
			return connection;
		}
		
		if(schemaSwitch || ObjectUtils.isEmpty(connection)) {
			connection.createStatement().execute(String.format("SET SEARCH_PATH = \"%s\";", "customers"));
			return connection;
		}
		connection.createStatement().execute(String.format("SET SEARCH_PATH = \"%s\";", tenantIdentifier));
		return connection;
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		connection.createStatement().execute(String.format("SET SEARCH_PATH = \"%s\";",Constants.DEFAULT_TENANT ));
		releaseAnyConnection(connection);
		
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

	public String getNewSchema() {
		return newSchema;
	}

	public void setNewSchema(String newSchema) {
		this.newSchema = newSchema;
	}

	public Boolean getSchemaSwitch() {
		return schemaSwitch;
	}

	public void setSchemaSwitch(Boolean schemaSwitch) {
		this.schemaSwitch = schemaSwitch;
	}
}
