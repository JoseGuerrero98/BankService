package com.microservice.bank.service;

import com.microservice.bank.model.Bank;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankService {
	
	public Flux<Bank> findAll();
	public Mono<Bank> findById(String id);
	public Mono<Boolean> existBank(String id);
	public Mono<Bank> createBank(Bank bank);
	public Mono<Bank> updateBank(Bank bank, String id);
	public Mono<Void> deleteBank(String id);
	
}
