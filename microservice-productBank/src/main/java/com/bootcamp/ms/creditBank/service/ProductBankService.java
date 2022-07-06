package com.bootcamp.ms.creditBank.service;

import com.bootcamp.ms.commons.entity.ProductBank;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductBankService {

    public Flux<ProductBank> findAll();
    public Mono<ProductBank> findById(String id);
    public Mono<ProductBank> save(ProductBank productBank);
    public Mono<Void> delete(ProductBank productBank);
}
