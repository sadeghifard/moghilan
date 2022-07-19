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

import reactor.core.publisher.Mono;

@Component("transactionWebSecurityFilter")
@Order(310)
@PostFilter("transactionWebSecurityFilter")
public class TransactionWebSecurityFilter extends ServerWebExchangeContextFilter {
	
	@Override
	public Mono<Void> filter (ServerWebExchange exchange, WebFilterChain chain){
		GenericMessage<Event<String, Object>> message = exchange.getAttribute(EXCHANGE_CONTEXT_ATTRIBUTE);
		message.getPayload().getKey(); // TODO: check key validation.
		// TODO: if key is invalid
		message.getPayload().setType(EventType.ROLLBACK);
		
		return chain.filter(exchange);
	}

}
