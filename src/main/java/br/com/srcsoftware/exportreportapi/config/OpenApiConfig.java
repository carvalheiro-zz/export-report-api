package br.com.srcsoftware.exportreportapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Geracao de Relatorios em PDF")
                .version("1.0")
                .description("Sistema de criação e gerenciamento de Relatorios em PDF.")
                .contact(new Contact().name("Suporte Técnico").email("dev@empresa.com")));
    }
}