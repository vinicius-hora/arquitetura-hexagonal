package br.com.boletojuros.application;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customApenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api Juros boleto")
                        .description("Api para calclular juros de boleto")
                        .contact(new Contact().name("Vinicius Bastos").email("vinicius_hora@live.com")
                        )
                        .version("1.0.0")
                );
    }
}
