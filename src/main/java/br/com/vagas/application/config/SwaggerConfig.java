package br.com.vagas.application.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("br.com.vagas.port.adapters.resource"))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Vagas.com Api", "Serviço para cadastro e registro de vagas/candidatos", "1.0.0", "",
                new Contact("Tiago Costa", "", "eutiagocosta@gmail.com"), "", "");
        return apiInfo;
    }
}
