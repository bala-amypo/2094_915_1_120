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
    public SwaggerConfig customOpenAPI() {
        // Define your server link (Update the URL to match your environment)
        Server server = new Server();
        server.setUrl("https://9270.pro604cr.amypo.ai/");
        server.setDescription("Development Server");

        // You can add more servers (e.g., Production) to the list
        return new SwaggerConfig()
                .info(new Info()
                        .title("Skill Barter Matchmaking Platform API")
                        .version("1.0")
                        .description("API documentation for the Skill Barter Platform"))
                .servers(List.of(server));
    }
}