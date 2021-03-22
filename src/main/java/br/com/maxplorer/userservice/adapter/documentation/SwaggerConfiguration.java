package br.com.maxplorer.userservice.adapter.documentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Maxplorer - user-service")
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.maxplorer.userservice.adapter.rest.controller"))
                .paths(PathSelectors.regex("/v.*"))
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Maxplorer by Roberto Amadeu Neto")
                .description("Explore the world.")
                .version("1.0")
                .contact(new Contact("Roberto Amadeu Neto", "http://www.robertoaneto.com.br", "robertoamadeuneto@gmail.com"))
                .build();
    }
}
