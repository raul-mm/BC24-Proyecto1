package com.bootcamp.ms.bankAccount;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Getter
@Setter
public class ClientConfig {

    @Value("${endpoint.client}")
    private String url;

}
