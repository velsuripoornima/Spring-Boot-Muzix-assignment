package com.stackroute.MusixAppAssignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

//enabling it is configuration file
@Configuration
<<<<<<< HEAD
//for application it self knows it is a config file
=======
//we are enabling swagger 
>>>>>>> 85187dd353ccc3e92a7d445e7ca09a6e661a8314
@EnableSwagger2
//enabling the swagger
public class SwaggerConfig {

<<<<<<< HEAD
    @Bean
    //for creating the bean
=======
    @Bean  //creating bean 
>>>>>>> 85187dd353ccc3e92a7d445e7ca09a6e661a8314
    public Docket trackApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stackroute.springboot"))
                .paths(regex("/track.*"))
                .build();
    }

}
