package br.com.giovanirizzato.todo.config;

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
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        .apis(RequestHandlerSelectors.any())
	        .paths(PathSelectors.any())
	        .build()
	        .useDefaultResponseMessages(true)
	        .apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	            .title("Simple Spring Boot REST API")
	            .description("Um exemplo de aplicação Spring Boot REST API")
	            .version("0.0.1")
	            .license("GPL-3.0 license")
	            .licenseUrl("https://github.com/GiovaniRizzato/Class-manager-backend-spring/blob/main/LICENSE")
	            .contact(new Contact("Giovani Rizzato", "https://github.com/GiovaniRizzato", "giovanirizzato@gmail.com"))
	            .build();
	}
}
