package org.javaweb.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "org.javaweb.*")
public class RASPBaselineApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RASPBaselineApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RASPBaselineApplication.class);
	}
}
