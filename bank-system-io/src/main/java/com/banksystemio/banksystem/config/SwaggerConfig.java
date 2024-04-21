package com.banksystemio.banksystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customAPI () {
        return new OpenAPI().info(new Info().title("Bank-IO API").version("1.0.0")
                .description("O Bank IO é um aplicativo simulador de Banco, que permite aos usuários realizar operações bancárias básicas. Como Criar contas, fazer depositos" +
                        "Saques, Transferencias, Historico de transações "));
    }
}
