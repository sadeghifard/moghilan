package com.sadeghifard.moghilan.config.filter;

import org.springframework.core.annotation.Order;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.reactive.ServerWebExchangeContextFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;

import com.sadeghifard.moghilan.domain.Event;
import com.sadeghifard.moghilan.enums.EventType;
import com.sadeghifard.moghilan.model.Transaction;
import com.sadeghifard.moghilan.service.impl.TransactionService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Order(310)
@AllArgsConstructor
@Component("postWebSecurityFilter")
@PostFilter("postWebSecurityFilter")
public class PostWebSecurityFilter extends ServerWebExchangeContextFilter {
	
	private final TransactionService transactionService;
	
	@Override
	public Mono<Void> filter (ServerWebExchange exchange, WebFilterChain chain){
		
		Object obj = exchange.getAttribute(EXCHANGE_CONTEXT_ATTRIBUTE);
		GenericMessage<Event<String, Object>> message = null;
		if(obj instanceof GenericMessage<?>) {
			 message = (GenericMessage<Event<String, Object>>) obj;
		}
		if(message != null) {
			EventType eventType = message.getPayload().getType();
			String eventKey = message.getPayload().getKey(); 
			Object eventData = message.getPayload().getData();
			Transaction transaction = transactionService.getTransactionByEventKey(eventKey);
			if((transaction == null && !eventType.equals(EventType.SAVE)) || 
				(transaction != null && eventType.equals(EventType.SAVE)) ||
				(eventData == null || eventKey == null)){
					message.getPayload().setType(EventType.ROLLBACK);
			}
		}
		return chain.filter(exchange);
	}

}
