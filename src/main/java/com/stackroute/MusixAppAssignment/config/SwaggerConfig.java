package com.stackroute.MusixAppAssignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
//for application it self knows it is a config file
@EnableSwagger2
//enabling the swagger
public class SwaggerConfig {

    @Bean
    //for creating the bean
    public Docket trackApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stackroute.springboot"))
                .paths(regex("/track.*"))
                .build();
    }

}
