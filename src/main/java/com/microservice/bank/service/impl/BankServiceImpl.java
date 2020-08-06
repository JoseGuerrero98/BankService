package com.microservice.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.bank.model.Bank;
import com.microservice.bank.repo.BankRepo;
import com.microservice.bank.service.BankService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankServiceImpl implements BankService {
	
	@Autowired
	private BankRepo repo;
	
	@Override
	public Flux<Bank> findAll() {
		return repo.findAll();
	}
	
	@Override
	public Mono<Bank> findById(String id) {
		return repo.findById(id);
	}
	
	public Mono<Boolean> existBank(String id) {
		return repo.existsById(id);
	}
	
	@Override
	public Mono<Bank> createBank(Bank bank) {
		return repo.save(bank);
	}
	
	@Override
	public Mono<Bank> updateBank(Bank bank, String id) {
		return repo.findById(id).flatMap(item -> {
			item.setName(bank.getName());
			return repo.save(item);
		}).switchIfEmpty(Mono.empty());
	}
	
	@Override
	public Mono<Void> deleteBank(String id) {
		try {
			
			return repo.findById(id).flatMap(item -> {
				return repo.delete(item);
			});
			
			
		} catch (Exception e) {
			return Mono.error(e);
		}
	}
	
}
