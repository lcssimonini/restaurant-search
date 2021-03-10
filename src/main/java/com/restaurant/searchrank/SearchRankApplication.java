package com.restaurant.searchrank;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.util.Collections;

@Slf4j
@EnableSwagger2
@SpringBootApplication
public class SearchRankApplication {

	private static final String ACCESS_URLS_MESSAGE_LOG =
			"\n\n Access URLs:\n----------------------------------------------------------\n\t External: \thttp://{}:{}{}/swagger-ui.html Profiles: {}\n----------------------------------------------------------\n";

	public static void main(final String[] args) {
		try {

			final SpringApplication app = new SpringApplication(SearchRankApplication.class);
			final Environment env = app.run().getEnvironment();
			log.info(
					ACCESS_URLS_MESSAGE_LOG,
					InetAddress.getLocalHost().getHostAddress(),
					env.getProperty("server.port"),
					env.getProperty("server.servlet.context-path"),
					env.getActiveProfiles());

		} catch (Exception e) {
			log.error("Start Error.", e);
		}
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.restaurant.searchrank"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Awesome restaurant search",
				"Restaurant search engine",
				"1",
				"Terms of service Url",
				new Contact("lucas simonini", "", "lucas.cs.simonini@gmail.com"),
				"License",
				"License URL",
				Collections.emptyList());
	}
}
