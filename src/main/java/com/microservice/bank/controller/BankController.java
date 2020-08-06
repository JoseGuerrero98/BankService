package com.microservice.bank.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.bank.model.Bank;
import com.microservice.bank.service.BankService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bank")
public class BankController {
	
	@Autowired
	private BankService service;
	
	@GetMapping("/")
	public Flux<Bank> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/findbyid/{id}")
	public Mono<Bank> findById(@PathVariable("id") String id) {
		return service.findById(id);
	}
	
	@GetMapping("/exist/{id}")
	public Mono<Boolean> existBank(@PathVariable("id") String id) {
		return service.existBank(id);
	}
	
	@PostMapping("/create")
	public Mono<ResponseEntity<Bank>> createBank(@RequestBody Bank bank) {
		return service.createBank(bank).map(item -> 
				ResponseEntity.created(URI.create("/bank".concat(item.getId())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(item)
				);
	}
	
	@PutMapping("/update/{id}")
	public Mono<ResponseEntity<Bank>> updateBank(@RequestBody Bank bank, @PathVariable("id") String id) {
		return service.updateBank(bank, id).map(item -> 
					ResponseEntity.created(URI.create("/bank".concat(item.getId())))
					.contentType(MediaType.APPLICATION_JSON)
					.body(item)
				);
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<ResponseEntity<Void>> deleteBank(@PathVariable("id") String id) {
		return service.deleteBank(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
}
