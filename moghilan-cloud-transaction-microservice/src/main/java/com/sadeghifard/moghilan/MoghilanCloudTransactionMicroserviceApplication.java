package com.sadeghifard.moghilan;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import com.sadeghifard.moghilan.config.transaction.MoghilanKafkaTransactionManager;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@SpringBootApplication
@EnableKafka
public class MoghilanCloudTransactionMicroserviceApplication {
	
	@Qualifier("transactionManager")
	private final MoghilanKafkaTransactionManager transactionManager;
	
	public static void main(String[] args) {
		SpringApplication.run(MoghilanCloudTransactionMicroserviceApplication.class, args);
	}

}
