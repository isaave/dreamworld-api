package com.barbie.dreamworld_api.infrastructure;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Barbie Dreamworld API")
                        .version("1.0")
                        .description("Esta API foi desenvolvida para o gerenciamento completo da coleção de Barbies, " +
                                "incluindo o controle de acessórios, eventos temáticos e integração com a Dreamhouse. " +
                                "Projeto acadêmico para o curso de Sistemas para Internet - Senac."));
    }
}