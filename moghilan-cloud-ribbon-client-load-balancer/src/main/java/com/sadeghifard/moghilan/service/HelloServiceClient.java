package com.sadeghifard.moghilan.service;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HelloServiceClient {
	
	@LoadBalanced
	private final RestTemplate restTemplate;
	
	public String sayHello(){
		return restTemplate.getForObject("http://moghilan-cloud-eureka-client-discovery-service/hello", String.class);
	}
												
}
