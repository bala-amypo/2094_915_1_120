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
        // Define the server URL
        Server localServer = new Server();
        localServer.setUrl("https://9002.pro604cr.amypo.ai/");
        localServer.setDescription("Local Development Server");

        return new OpenAPI()
                .info(new Info()
                        .title("API Rate Limiter")
                        .version("1.0")
                        .description("Documentation for API Rate Limiter endpoints"))
                .servers(List.of(localServer));
    }
}