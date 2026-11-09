package com.swarupdhavan.appointment_booking_system_prototype.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@Configuration
public class ProjectConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("MediSync Prototype")
                                .description("This is prototype for showcase of appointment booking system for hospitals")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .name("Swarup Dhavan")
                                                .email("swarup93@proton.me")
                                )
                );
    }
}
