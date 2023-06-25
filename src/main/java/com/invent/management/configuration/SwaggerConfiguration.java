package com.invent.management.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /** API prefix for rest endpoints, based on profile settings. */
    @Value("${management.api.prefix:}")
    private String apiPrefix;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .forCodeGeneration(true)
                .apiInfo(apiInfo())
                .select()
                .paths(regex(apiPrefix + "/v1/.*"))
                .apis(RequestHandlerSelectors.basePackage("com.springtutorial"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Management REST API")
                .description("Management application allows to perform CRUD operation on any user.")
                .version("Version 1.0")
                .build();
    }
}
