package com.bootcamp.ms.commons.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productBank")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductBank {

    @Id
    private String id;
    private String description;

}
