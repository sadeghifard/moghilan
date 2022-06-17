package com.sadeghifard.moghilan.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@FeignClient("moghilan-cloud-eureka-client-discovery-service")
public interface HelloClientService {
	
	@GetMapping("/hello")
	String sayHello();

}
