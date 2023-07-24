package com.demo.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.common.Constants;
import com.demo.repository.CustomerRepository;

@Configuration
public class FlywayConfig {

	@Bean
	public Flyway flyway(DataSource dataSource) {
		Flyway flyway = Flyway.configure()
				.locations("/db/migration/customer")
				.dataSource(dataSource)
				.schemas(Constants.DEFAULT_TENANT)
				.load();
		flyway.migrate();
		return flyway;
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(CustomerRepository customerRepository, DataSource dataSource) {
		return args -> {
			customerRepository.findAll().forEach(cust -> {
				String tenant = cust.getUsername();
				Flyway flyway = Flyway.configure()
						.locations("/db/migration/tenants")
						.dataSource(dataSource)
						.schemas(tenant)
						.load();
				flyway.migrate();
			});
		};
	}
}
