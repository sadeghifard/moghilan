package com.sadeghifard.moghilan.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.streams.errors.StreamsException;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.sadeghifard.moghilan.domain.Account;
import com.sadeghifard.moghilan.domain.Customer;
import com.sadeghifard.moghilan.domain.Event;
import com.sadeghifard.moghilan.domain.Payment;
import com.sadeghifard.moghilan.enums.EventType;
import com.sadeghifard.moghilan.utile.Utility;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@AllArgsConstructor
@Component
@Transactional
public class TransactionCompositionService {
	private final WebAccountService webAccountService;
	private final WebCustomerService webCustomerService;
	private final WebPaymentService webPaymentService;
	private final StreamBridge streamBridge;
	private final Scheduler publishEventScheduler;
	private Event<String, Account> accountEvent;
	private Event<String, Customer> customerEvent;
	private Event<String, Payment> paymentEvent;
	private Throwable dlqError;
	
	@KafkaListener(topics = {"${spring.cloud.stream.kafka.bindings.account-service-in-0.consumer.dlq-name:errors}",
			                 "${spring.cloud.stream.kafka.bindings.customer-service-in-0.consumer.dlq-name:errors}",
			                 "${spring.cloud.stream.kafka.bindings.payment-service-in-0.consumer.dlq-name:errors}",
						     "${spring.cloud.stream.kafka.binder.consumer-properties.dlq-name:errors}"
							})
	private void processKafkaErrors(Object message) {
		this.dlqError = ((GenericMessage<Event<String,? extends Throwable>>) message).getPayload().getData();
	}
	
	@KafkaListener(topics = "${spring.cloud.stream.bindings.account-service-in-0.consumer.destination:accounts}")
	private void processAccount(Object message) {
		this.accountEvent = ((GenericMessage<Event<String, Account>>) message).getPayload();
	}
	
	@KafkaListener(topics = "${spring.cloud.stream.bindings.customer-service-in-0.consumer.destination:customers}")
	private void processCustomer(Object message) {
		this.customerEvent = ((GenericMessage<Event<String, Customer>>) message).getPayload();
	}
	
	@KafkaListener(topics = "${spring.cloud.stream.bindings.payment-service-in-0.consumer.destination:payments}")
	private void processPayment(@Payload Object message) {
		this.paymentEvent = ((GenericMessage<Event<String, Payment>>) message).getPayload();
	}
	
	private void checkConsumers() {
		if(dlqError != null || (accountEvent.getData() == null || customerEvent.getData() == null || paymentEvent.getData() == null)) {
			sendMessage("errors-out-0", new Event(EventType.ROLLBACK, Utility.tokenGenerator(), new StreamsException("Rollback")));
			throw new StreamsException("Rollback");
		}
	}
	
	@PostMapping(value = "/transaction")
	public Mono<Void> save() {
		try {
			checkConsumers();
			if(accountEvent.getType().equals(EventType.SAVE) &&
			   customerEvent.getType().equals(EventType.SAVE) &&
			   paymentEvent.getType().equals(EventType.SAVE)) {
				Account account = webAccountService.saveAccount((Mono.just(accountEvent.getData())));
				Customer customer = webCustomerService.saveCustomer((Mono.just(customerEvent.getData())));
				Payment payment = webPaymentService.savePayment((Mono.just(paymentEvent.getData())));
				List<Mono<Void>> messages = new ArrayList<>();
				messages.add(Mono.fromRunnable(() -> sendMessage("accounts-out-0", new Event(EventType.COMMIT, Utility.tokenGenerator(), account))));
				messages.add(Mono.fromRunnable(() -> sendMessage("customers-out-0", new Event(EventType.COMMIT, Utility.tokenGenerator(), customer))));
				messages.add(Mono.fromRunnable(() -> sendMessage("payments-out-0", new Event(EventType.COMMIT, Utility.tokenGenerator(), payment))));
				return Mono.firstWithValue(messages).subscribeOn(publishEventScheduler).then();
			}
			return Mono.fromRunnable(() -> sendMessage("errors-out-0", new Event(EventType.ROLLBACK, Utility.tokenGenerator(), new StreamsException("Rollback"))))
					.subscribeOn(publishEventScheduler).then();
		
		} catch (Exception e) {
			return Mono.fromRunnable(() -> sendMessage("errors-out-0", new Event(EventType.ROLLBACK, Utility.tokenGenerator(), new StreamsException("Rollback"))))
					.subscribeOn(publishEventScheduler).then();
		}
	}
	
	@PutMapping(value = "/transaction")
	public Mono<Void> update() {
		try {
			checkConsumers();
			if(accountEvent.getType().equals(EventType.UPDATE) &&
			   customerEvent.getType().equals(EventType.UPDATE) &&
			   paymentEvent.getType().equals(EventType.UPDATE)) {
				Account account = webAccountService.updateAccount((Mono.just(accountEvent.getData())));
				Customer customer = webCustomerService.updateCustomer((Mono.just(customerEvent.getData())));
				Payment payment = webPaymentService.updatePayment((Mono.just(paymentEvent.getData())));
				List<Mono<Void>> messages = new ArrayList<>();
				messages.add(Mono.fromRunnable(() -> sendMessage("accounts-out-0", new Event(EventType.COMMIT, Utility.tokenGenerator(), account))));
				messages.add(Mono.fromRunnable(() -> sendMessage("customers-out-0", new Event(EventType.COMMIT, Utility.tokenGenerator(), customer))));
				messages.add(Mono.fromRunnable(() -> sendMessage("payments-out-0", new Event(EventType.COMMIT, Utility.tokenGenerator(), payment))));
				return Mono.firstWithValue(messages).subscribeOn(publishEventScheduler).then();
			}
			return Mono.fromRunnable(() -> sendMessage("errors-out-0", new Event(EventType.ROLLBACK, Utility.tokenGenerator(), new StreamsException("Rollback"))))
					.subscribeOn(publishEventScheduler).then();
			
		} catch (Exception e) {
			return Mono.fromRunnable(() -> sendMessage("errors-out-0", new Event(EventType.ROLLBACK, Utility.tokenGenerator(), new StreamsException("Rollback"))))
					.subscribeOn(publishEventScheduler).then();
		}
	}
	
	private void sendMessage(String destination, Event event) {
	    GenericMessage<?> message = (GenericMessage<?>) MessageBuilder.withPayload(event)
	      .setHeader("partitionKey", event.getKey())
	      .build();
	    streamBridge.send(destination, message);
	}

}
