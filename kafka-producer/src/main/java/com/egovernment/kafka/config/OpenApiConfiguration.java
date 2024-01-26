package com.egovernment.kafka.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Government Kafka Producer API")
                        .version("1.0.0")
                        .description("Kafka-Producer for votes")
                        .contact(new Contact()
                                .name("Yana Metodieva")
                                .email("yana.metodieva0@gmail.com"))
                        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html")))
                .tags(List.of(new Tag().name("Kafka Messaging").description("Operations related to Kafka messaging")));
    }


}
