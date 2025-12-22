package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Use the URL from your browser preview
        Server previewServer = new Server();
        previewServer.setUrl("https://9002.pro604cr.amypo.ai");
        previewServer.setDescription("Preview Environment");

        return new OpenAPI()
                .info(new Info()
                        .title("API Rate Limiter")
                        .version("1.0")
                        .description("API documentation for the Rate Limiter project"))
                .servers(List.of(previewServer));
    }
}