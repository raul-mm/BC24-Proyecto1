package com.bootcamp.ms.creditBank.controller;

import com.bootcamp.ms.commons.entity.BankCredit;
import com.bootcamp.ms.commons.entity.Client;
import com.bootcamp.ms.commons.entity.ProductBank;
import com.bootcamp.ms.creditBank.service.BankCreditService;
import com.bootcamp.ms.creditBank.service.ClientService;
import com.bootcamp.ms.creditBank.service.ProductBankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/bankCredit")
public class BankCreditController {

    @Autowired
    private BankCreditService bankCreditService;

    @Autowired
    private ProductBankService productBankService;

    @Autowired
    private ClientService clientService;

    private final Logger logger = LoggerFactory.getLogger(BankCreditController.class);

    @GetMapping(value = "/all")
    public Flux<BankCredit> getAll() {
        return bankCreditService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<BankCredit> findById(@PathVariable String id) {
        return bankCreditService.findById(id);
    }

    @GetMapping(value = "getAvailableBalancesxcliente/{id}")
    public Mono<BankCredit> getAvailableBalances(@PathVariable String id) {

        return bankCreditService.findByIdCliente(id);
    }

    @PostMapping(value = "/chargeCreditCardConsumption")
    public Mono<BankCredit> chargeCreditCardConsumption(@RequestBody BankCredit bankCredit) {

        Mono<ProductBank> productBank = productBankService.find(bankCredit.getIdProduct());
        Mono<Client> clientMono = clientService.find(bankCredit.getIdClient());

        productBank.subscribe(r -> bankCredit.setProductBank(r));

//        productBank.flatMap(acc -> {
//            bankCredit.setProductBank(acc);
//            return Mono.empty();
//        });

        clientMono.flatMap(acc -> {
            bankCredit.setClient(acc);
            return Mono.empty();
        });

        bankCredit.maxMovement(bankCredit.getType());

        return bankCreditService.save(bankCredit);
    }

    @PutMapping(value = "makePaymentsCreditProducts/{id}")
    public Mono<BankCredit> update(@PathVariable String id, @RequestBody BankCredit bankCredit) {
        return bankCreditService.findById(id).flatMap(p -> {
            p.setId(id);
            p.setAmount(p.getAmount() - bankCredit.getAmount());
            return bankCreditService.save(p);
        });
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return bankCreditService.findById(id).flatMap(p -> {
           return bankCreditService.delete(p);
        });
    }

}
