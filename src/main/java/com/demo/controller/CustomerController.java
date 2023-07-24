package com.demo.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.LoginDTO;
import com.demo.entity.Customer;
import com.demo.entity.Device;
import com.demo.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/login")
	@PermitAll
	public ResponseEntity<AccessTokenResponse> login(@RequestBody LoginDTO loginDTO){
		return new ResponseEntity<AccessTokenResponse>(customerService.login(loginDTO), HttpStatus.OK);
	}

	@PostMapping("/onboardCustomer")
	@RolesAllowed("SUPER_ADMIN")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		return new ResponseEntity<Customer>(customerService.addCustomer(customer), HttpStatus.CREATED);
	}
	
	@PostMapping("/onboardDevice")
	@RolesAllowed({"SUPER_ADMIN","ADMIN"})
	public ResponseEntity<Device> addDevice(@RequestBody Device device){
		return new ResponseEntity<Device>(customerService.addDevice(device), HttpStatus.CREATED);
	}
	
	@GetMapping("/customers")
	@RolesAllowed({"SUPER_ADMIN","ADMIN"})
	public ResponseEntity<List<Customer>> getAllCustomer(){
		return new ResponseEntity<List<Customer>>(customerService.getAllCustomer(), HttpStatus.OK);
	}
	
	@GetMapping("/devices")
	@RolesAllowed({"SUPER_ADMIN","ADMIN"})
	public ResponseEntity<List<Device>> getAllDevice(){
		return new ResponseEntity<List<Device>>(customerService.getAllDevices(), HttpStatus.OK);
	}
}
