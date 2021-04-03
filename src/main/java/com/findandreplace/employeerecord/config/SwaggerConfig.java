package com.findandreplace.employeerecord.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //LinkDiscovery exception resolved after updating the swagger dependency.
    //De-activating the bean definition.
    /*
    @Primary
    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }
*/
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.findandreplace.employeerecord"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails(){
        return new ApiInfo(
                "Employee Record Application",
                "Demo for personal reference",
                "1.0",
                "Terms of service url",
                new Contact("Mr. Lazy Programmer", "Application url", "lazy@programmer.com"),
                "API or Application License",
                "License Url",
                Collections.emptyList());
    }
}
