package com.restaurant.searchrank;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@Slf4j
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
}
