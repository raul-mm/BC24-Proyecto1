package com.bootcamp.ms.commons.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "bankAccount")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {

    @Id
    private String id;
    private String type;
    private Double maintenanceFee;
    private Integer maxMovement;
    private Date date;
    private Double amount;
    private String idProduct;
    private String idClient;
    private Client client;
    private ProductBank productBank;

    public void maintenanceFee(String type) {

        switch (type) {
            case "Ahorro":
                this.maxMovement = 5;
                break;
            case "Cuenta Corriente":
                this.maintenanceFee = 2.50;
                break;
            case "Plazo Fijo":
                this.maxMovement = 1;
                break;
        }
    }
}
