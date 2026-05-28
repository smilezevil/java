package com.hotel.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hotelProjectOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hotel Management REST API")
                        .description("API для управління системою готелю: бронювання, гості, кімнати та послуги.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Demyanchuk Nastya")
                                .url("https://github.com/smilezevil")
                                .email("demyanchuknastya2018@gmail.com"))
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .servers(List.of(new Server().url("._.")));
    }
}