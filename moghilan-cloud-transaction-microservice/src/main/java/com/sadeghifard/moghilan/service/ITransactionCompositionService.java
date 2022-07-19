package com.sadeghifard.moghilan.service;

import reactor.core.publisher.Mono;

public interface ITransactionCompositionService {
	
	public Mono<Void> saveTransaction();
	public Mono<Void> updateTransaction();

}
