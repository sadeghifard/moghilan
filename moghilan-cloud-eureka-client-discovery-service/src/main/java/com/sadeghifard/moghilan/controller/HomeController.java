package com.sadeghifard.moghilan.controller;

import java.net.URI;
import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private final DiscoveryClient discoveryClient;
	
	public URI serviceURL() {
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances("moghilan-cloud-eureka-client-discovery-service");
		if(serviceInstances != null && !serviceInstances.isEmpty()) {
			return serviceInstances.get(0).getUri();
		}
		return null;
	}
}
