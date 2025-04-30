package com.univesp.aquinopredio.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI springAquinoPredioOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Aqui no Prédio")
                        .description("Projeto Aqui no Prédio, realizado como Projeto Integrador da Universidade Virtual do Estado de São Paulo (UNIVESP)")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Universidade Virtual do Estado de São Paulo (UNIVESP)")
                                .url("https://univesp.br/"))
                        .contact(new Contact()
                                .name("Aqui no Prédio")
                                .url("https://github.com/Aqui-no-Predio")));
    }

    @Bean
    OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {

        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

                ApiResponses apiResponses = operation.getResponses();

                apiResponses.addApiResponse("200", createApiResponse("Sucesso"));
                apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido"));
                apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído"));
                apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição"));
                apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado"));
                apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido"));
                apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado"));
                apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação"));
            }));
        };
    }

    private ApiResponse createApiResponse(String message) {
        return new ApiResponse().description(message);
    }
}