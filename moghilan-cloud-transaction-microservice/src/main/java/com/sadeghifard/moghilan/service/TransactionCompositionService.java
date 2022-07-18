package com.sadeghifard.moghilan.service;

import org.apache.kafka.streams.errors.StreamsException;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import com.sadeghifard.moghilan.domain.Account;
import com.sadeghifard.moghilan.domain.Customer;
import com.sadeghifard.moghilan.domain.Event;
import com.sadeghifard.moghilan.domain.Payment;
import com.sadeghifard.moghilan.enums.EventType;
import com.sadeghifard.moghilan.utile.Utility;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
@Transactional
public class TransactionCompositionService {
	private final WebAccountService webAccountService;
	private final WebCustomerService webCustomerService;
	private final WebPaymentService webPaymentService;
	private final StreamBridge streamBridge;
	private Object accountPayload;
	private Object customerPayload;
	private Object paymentPayload;
	
	@KafkaListener(topics = "${spring.cloud.stream.bindings.account-service-in-0.consumer.destination:accounts}")
	private void processAccount(@Payload Object payload) {
		this.accountPayload = payload;
	}
	
	@KafkaListener(topics = "${spring.cloud.stream.bindings.customer-service-in-0.consumer.destination:customers}")
	private void processCustomer(@Payload Object payload) {
		this.customerPayload = payload;
	}
	
	@KafkaListener(topics = "${spring.cloud.stream.bindings.payment-service-in-0.consumer.destination:payments}")
	private void processPayment(@Payload Object payload) {
		this.paymentPayload = payload;
	}
	
	private void readConsumers() {
		if(accountPayload == null || customerPayload == null || paymentPayload == null) {
			sendMessage("errors", new Event(EventType.ROLLBACK, Utility.tokenGenerator(), new StreamsException("Rollback")));
			throw new StreamsException("Rollback");
		}
	}
	
	@PostMapping(value = "/transaction")
	public void save() {
		try {
			readConsumers();
			Account account = webAccountService.saveAccount((Mono<Account>) accountPayload);
			Customer customer = webCustomerService.saveCustomer((Mono<Customer>) customerPayload);
			Payment payment = webPaymentService.savePayment((Mono<Payment>) paymentPayload);
			sendMessage("accounts", new Event(EventType.COMMIT, Utility.tokenGenerator(), account));
			sendMessage("customers", new Event(EventType.COMMIT, Utility.tokenGenerator(), customer));
			sendMessage("payments", new Event(EventType.COMMIT, Utility.tokenGenerator(), payment));
		
		} catch (Exception e) {
			sendMessage("errors", new Event(EventType.ROLLBACK, Utility.tokenGenerator(), new StreamsException("Rollback")));
		}
	}
	private void sendMessage(String destination, Event event) {
	  	event.setKey(Utility.tokenGenerator());
	    GenericMessage<?> message = (GenericMessage<?>) MessageBuilder.withPayload(event)
	      .setHeader("partitionKey", event.getKey())
	      .build();
	    streamBridge.send(destination, message);
	}

}
