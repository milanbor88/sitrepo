package com.example.configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket productAPI(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller.rest"))
                .paths(paths())
                .build()
                .apiInfo(apiInfo());
    }


    private Predicate<String> paths() {
        return Predicates.or(
                regex("/rest-api.*"));
    }
    ApiInfo apiInfo() {
        return new ApiInfo(
                "SitRepo",
                "Car accident",
                "API TOS",
                "Terms of service",
                new Contact("Uljarevic","www.example.com","mail"),
                "Licence by api", "Api licence url", Collections.emptyList());

    }
}
