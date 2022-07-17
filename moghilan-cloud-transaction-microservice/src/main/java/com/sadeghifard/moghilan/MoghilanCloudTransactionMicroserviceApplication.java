package com.sadeghifard.moghilan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class MoghilanCloudTransactionMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoghilanCloudTransactionMicroserviceApplication.class, args);
	}

}
