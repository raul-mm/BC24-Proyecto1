package com.bootcamp.ms.creditBank.repository;

import com.bootcamp.ms.commons.entity.ProductBank;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductBankRepository extends ReactiveMongoRepository<ProductBank, String> {
}
