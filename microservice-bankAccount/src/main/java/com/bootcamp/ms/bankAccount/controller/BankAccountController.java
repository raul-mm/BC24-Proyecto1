package com.bootcamp.ms.bankAccount.controller;

import com.bootcamp.ms.bankAccount.service.BankAccountService;
import com.bootcamp.ms.bankAccount.service.ClientService;
import com.bootcamp.ms.bankAccount.service.ProductBankService;
import com.bootcamp.ms.commons.entity.BankAccount;
import com.bootcamp.ms.commons.entity.Client;
import com.bootcamp.ms.commons.entity.ProductBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private ProductBankService productBankService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public Flux<BankAccount> getAll(){
        return bankAccountService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<BankAccount> find(@PathVariable String id){
        return bankAccountService.findById(id);
    }

    @PostMapping(value = "/create")
    public Mono<Object> createBankAccount(@RequestBody BankAccount bankAccount){

        Mono<BankAccount> bankAccountMono1 = null;
        Mono<Client> clientMono;

        Mono<ProductBank> productBank = productBankService.find(bankAccount.getIdProduct());

        bankAccount.maintenanceFee(bankAccount.getType());

        clientMono = clientService.find(bankAccount.getIdClient());

        clientMono.flatMap(c -> {

            bankAccount.setClient(c);

            if(c.getType().equalsIgnoreCase("personal")){
                if(bankAccount.getType().equalsIgnoreCase("ahorro") || bankAccount.getType().equalsIgnoreCase("cuenta corriente")){
                     Mono<BankAccount> bankAccountMono = bankAccountService.searchBankAccountByTypeAndIdClient(bankAccount.getType(), c.getId());

                     if(bankAccountMono == null){
                         bankAccountMono = bankAccountService.save(bankAccount);
                     }
                }
            }else{

            }

            return Mono.empty();
        }).subscribe();

        return Mono.just("creado correctamente");
    }
//
//    @PutMapping(value = "/update/{id}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public BankAccount updateBankAccount(@PathVariable String id, @RequestBody BankAccount bankAccount){
//        Mono<BankAccount> bankAccountUpdate = bankAccountService.findById(id);
//
//        bankAccountUpdate.setType(bankAccount.getType());
//        bankAccountUpdate.setMaintenanceFee(bankAccount.getMaintenanceFee());
//        bankAccountUpdate.setMaxMovement(bankAccount.getMaxMovement());
//        bankAccountUpdate.setDate(bankAccount.getDate());
//        bankAccountUpdate.setAmount(bankAccount.getAmount());
//        bankAccountUpdate.setIdProduct(bankAccount.getIdProduct());
//        bankAccountUpdate.setIdClient(bankAccount.getIdClient());
//
//        return bankAccountService.save(bankAccountUpdate);
//    }
//
//    @DeleteMapping(value = "/delete/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteBankAccount(@PathVariable String id) {
//        bankAccountService.deleteById(id);
//    }
//
//    @PutMapping(value = "/deposit/{id}/{amount}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String deposit(@PathVariable String id, @PathVariable double amount){
//        Mono<BankAccount> currentBankAccount = bankAccountService.findById(id);
//
//        double currentAmount = currentBankAccount.getAmount();
//        int currentMovement = currentBankAccount.getMaxMovement();
//        currentAmount += amount;
//
//        if(currentMovement > 0){
//            currentMovement -= 1;
//        }
//
//        currentBankAccount.setAmount(currentAmount);
//        currentBankAccount.setMaxMovement(currentMovement);
//
//        bankAccountService.save(currentBankAccount);
//
//        return "El monto actual es " + currentAmount;
//    }
//
//    @PutMapping(value = "/withdraw/{id}/{amount}")
//    //@CircuitBreaker(name = "customerContactInfoService", fallbackMethod = "metodoAlternativo")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String withdraw(@PathVariable String id, @PathVariable double amount){
//        BankAccount currentBankAccount = bankAccountService.findById(id);
//
//        double currentAmount = currentBankAccount.getAmount();
//        int currentMovement = currentBankAccount.getMaxMovement();
//        currentAmount -= amount;
//
//        if(currentMovement > 0){
//            currentMovement -= 1;
//        }
//
//        String messageSuccess = "El monto actual es " + currentAmount;
//        String messageError = "No tiene fondos suficientes";
//
//        if(currentAmount > 0){
//            currentBankAccount.setAmount(currentAmount);
//            currentBankAccount.setMaxMovement(currentMovement);
//
//            bankAccountService.save(currentBankAccount);
//
//            return messageSuccess;
//        }else{
//            return messageError;
//        }
//    }
//
//    @PutMapping(value = "/transfer/{id}/{amount}/{idDestination}")
//    //@CircuitBreaker(name = "customerContactInfoService", fallbackMethod = "metodoAlternativo")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String transfer(@PathVariable String id, @PathVariable double amount, @PathVariable String idDestination){
//        BankAccount currentBankAccount = bankAccountService.findById(id);
//        BankAccount bankAccountDestination = bankAccountService.findById(idDestination);
//
//        String message = "La transferencia se realizó correctamente";
//
//        if(currentBankAccount != null && bankAccountDestination != null){
//            double currentAmount = currentBankAccount.getAmount();
//            int currentMovement = currentBankAccount.getMaxMovement();
//
//            currentAmount -= amount;
//
//            if(currentMovement > 0){
//                currentMovement -= 1;
//            }
//
//            if(currentAmount > 0){
//                currentBankAccount.setAmount(currentAmount);
//                currentBankAccount.setMaxMovement(currentMovement);
//
//                double currentAmountDestination = bankAccountDestination.getAmount() + amount;
//                bankAccountDestination.setAmount(currentAmountDestination);
//
//                bankAccountService.save(currentBankAccount);
//                bankAccountService.save(bankAccountDestination);
//            }else{
//                message = "No tiene fondos suficientes";
//            }
//        }else{
//            message = "Error una de las cuentas no existe";
//        }
//        return message;
//    }
//
//    @GetMapping(value = "/checkBalance/{id}")
//    public double checkBalance(@PathVariable String id){
//        return bankAccountService.checkBalance(id);
//    }
//
//    @GetMapping(value = "/consultMovements/{id}")
//    public int consultMovements(@PathVariable String id){
//        return bankAccountService.consultMovements(id);
//    }

}
