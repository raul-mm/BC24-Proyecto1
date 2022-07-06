package com.bootcamp.ms.bankAccount.service;

import com.bootcamp.ms.commons.entity.BankAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankAccountService {

    Mono<BankAccount> searchBankAccountByTypeAndIdClient(String type, String idClient);

    Flux<BankAccount> findAll();

    Mono<BankAccount> findById(String id);

    Mono<BankAccount> save(BankAccount bankAccount);

    Mono<Void> delete(String id);

    Mono<Double> checkBalance(String id);
}
