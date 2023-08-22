package com.students.studentservice.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecuritySchemes({
        @SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP
                , scheme = "bearer", bearerFormat = "JWT")
})
public class SwaggerConfig{

    @Value("${application.version}")
    private String version;

    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .info(new Info()
                        .title("Java Assessment")
                        .description("Building rest Api")
                        .version(version));
    }

    @Bean
    public GroupedOpenApi usersEndpoint(){
        return  GroupedOpenApi
                .builder()
                .group("Students")
                .pathsToMatch("/api/v1/students/**").build();
    }

}