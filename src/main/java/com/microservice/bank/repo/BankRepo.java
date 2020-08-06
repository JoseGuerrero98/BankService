package com.microservice.bank.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.microservice.bank.model.Bank;

@Repository
public interface BankRepo extends ReactiveMongoRepository<Bank, String>{

}
