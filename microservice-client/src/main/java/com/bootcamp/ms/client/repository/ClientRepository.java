package com.bootcamp.ms.client.repository;

import com.bootcamp.ms.commons.entity.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClientRepository extends ReactiveMongoRepository<Client, String> {
}
