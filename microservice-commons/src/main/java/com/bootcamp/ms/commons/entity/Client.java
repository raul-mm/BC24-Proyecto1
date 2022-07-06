package com.bootcamp.ms.commons.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String age;
    private String type;
}
