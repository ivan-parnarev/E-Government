package com.egovernment.main.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openAPI(){
       return new OpenAPI()
                .info(new Info()
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Yana Metodieva, Nedko Karaivanov")
                                .email("yana.metodieva0@gmail.com, nedko2016@gmail.com"))
                        .description("E-Government Project"));
    }

}
