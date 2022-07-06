package com.bootcamp.ms.client.service;

import com.bootcamp.ms.commons.entity.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    public Flux<Client> findAll();
    public Mono<Client> findById(String id);
    public Mono<Client> save(Client client);
    public Mono<Void> delete(Client client);
}
