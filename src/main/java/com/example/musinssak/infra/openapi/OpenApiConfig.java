package com.example.musinssak.infra.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    private static final String SCHEME = "BearerAuth";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Musinsa Shop API").version("v1"))
                .components(new Components().addSecuritySchemes(
                        SCHEME,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                ))
                // 전역 보호(전체 API에 Authorization 적용). 특정 API만 보호하고 싶으면 이 줄 빼고 메서드에 @SecurityRequirement 사용.
                .security(List.of(new SecurityRequirement().addList(SCHEME)));
    }
}
