package com.demo.service;

import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.common.AppConfig;
import com.demo.dto.LoginDTO;
import com.demo.entity.Customer;
import com.demo.entity.Device;
import com.demo.repository.CustomerRepository;
import com.demo.repository.DeviceRepository;
import com.demo.utills.KeycloakInstanceBuilder;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private FlywayService flywayService;
	
	@Autowired
	private AppConfig appConfig;
	
	private KeycloakInstanceBuilder keycloakInstanceBuilder;
	
	private Keycloak keycloak = null;
	
	public AccessTokenResponse login(LoginDTO loginDTO) {	
		AppConfig config = new AppConfig();
		BeanUtils.copyProperties(appConfig, config);
		config.setKeycloackUsername(loginDTO.getUsername());
		config.setKeycloackPassword(loginDTO.getPassword());
		keycloak = keycloakInstanceBuilder.getInstance(config);
		return keycloak.tokenManager().getAccessToken();
	}
	
	public Customer addCustomer(Customer customer) {	
		customer = customerRepository.save(customer);
		flywayService.initDatabase(customer.getUsername());
		return customer;
	}
	
    public List<Customer> getAllCustomer() {
    	return customerRepository.findAll();
	}
    
    public Device addDevice(Device device) {
		return deviceRepository.save(device);
	}
    
    public List<Device> getAllDevices() {
    	return deviceRepository.findAll();
	}
}
