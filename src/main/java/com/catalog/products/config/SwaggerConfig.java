package com.catalog.products.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public OpenAPI swagger() {
        return new OpenAPI()
                .info(metaInfo());
    }

    private Info metaInfo() {
        return new Info()
                .title("Products Catalog API REST")
                .description("API REST of products catalog")
                .version("1.0")
                .termsOfService("Terms of Service Ednilson, https://github.com/Ednilsonpalhares," +
                                "ednilsonpalhares.@hotmail.com ");
    }
}
