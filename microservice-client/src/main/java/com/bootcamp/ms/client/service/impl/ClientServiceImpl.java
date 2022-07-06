package com.bootcamp.ms.client.service.impl;

import com.bootcamp.ms.commons.entity.Client;
import com.bootcamp.ms.client.repository.ClientRepository;
import com.bootcamp.ms.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientRepository clientRepository;

  @Override
  public Flux<Client> findAll() {
    return clientRepository.findAll();
  }

  @Override
  public Mono<Client> findById(String id) {
    return clientRepository.findById(id);
  }

  @Override
  public Mono<Client> save(Client client) {
    return clientRepository.save(client);
  }

  @Override
  public Mono<Void> delete(Client client) {
    return clientRepository.delete(client);
  }
}
