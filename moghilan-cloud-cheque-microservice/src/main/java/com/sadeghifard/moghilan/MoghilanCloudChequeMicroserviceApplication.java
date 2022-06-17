package com.sadeghifard.moghilan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MoghilanCloudChequeMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoghilanCloudChequeMicroserviceApplication.class, args);
	}

}
