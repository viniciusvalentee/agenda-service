package com.valente.agenda.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de agendamento")
                        .version("1.0.0")
                        .description("API de agendamento de consultas médicas")
                        .termsOfService("Termos de serviço")
                        .contact(new Contact()
                                .name("Vinicius Valente")
                                .email("viniciusvramirez0108@gmail.com")
                                .url("https://github.com/viniciusvalentee/agenda-service"))
                        .license(new License()
                                .name("Licença da API")
                                .url("https://github.com/viniciusvalentee/agenda-service")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**") // Defina os endpoints que devem ser documentados
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/admin/**") // Defina os endpoints administrativos
                .build();
    }
}
