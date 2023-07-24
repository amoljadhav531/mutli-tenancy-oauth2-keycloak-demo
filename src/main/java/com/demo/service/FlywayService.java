package com.demo.service;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Service;

@Service
public class FlywayService {

	private DataSource dataSource;

	public FlywayService(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void initDatabase(String schema) {
		Flyway flyway = Flyway.configure()
				.locations("/db/migration/tenants")
				.dataSource(dataSource)
				.schemas(schema)
				.load();
		flyway.migrate();
	}
}
