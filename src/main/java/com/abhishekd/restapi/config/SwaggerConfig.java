package com.abhishekd.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    /**
     * Custom Configuration for Swagger
     * @return
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.abhishekd.restapi.controllers"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(metaData());
    }


    private ApiInfo metaData() {

        Contact contact = new Contact("Abhishek", "", "");

        return new ApiInfo(
                            "REST Services API Documentation",
                            "E-commerce APIs",
                            "1.0",
                            "",
                            contact,
                            "Apache License Version 2.0",
                            "http://www.apache.org/licenses/LICENSE-2.0",
                             new ArrayList<>()
        );
    }
}
