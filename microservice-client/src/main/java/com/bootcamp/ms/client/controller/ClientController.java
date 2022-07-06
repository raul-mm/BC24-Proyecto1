package com.bootcamp.ms.client.controller;

import com.bootcamp.ms.commons.entity.Client;
import com.bootcamp.ms.client.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public Flux<Client> getAll(){

        logger.info("Mostrando la lista de los clientes de la bd");
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Client> findClient(@PathVariable String id){

        logger.info("Buscando al cliente por el id: " + id);
        return clientService.findById(id);
    }

    @PostMapping("/save")
    public Mono<Client> save(@RequestBody Client clientMono){

        logger.info("Registrando al cliente en la bd");
        return clientService.save(clientMono);
    }

    @PutMapping("/{id}")
    public Mono<Client> edit(@RequestBody Client client, @PathVariable String id){

        logger.info("Editando al cliente con el id: " + id);

        return clientService.findById(id).flatMap(c -> {
            c.setFirstName(client.getFirstName());
            c.setLastName(client.getLastName());
            c.setDocumentNumber(client.getDocumentNumber());
            c.setAge(client.getAge());
            c.setType(client.getType());

            return clientService.save(c);
        });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> eliminar(@PathVariable String id){
        return clientService.findById(id).flatMap(c -> {
            return clientService.delete(c);
        });
    }
}
