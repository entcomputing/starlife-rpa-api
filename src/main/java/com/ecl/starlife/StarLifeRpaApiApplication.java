package com.ecl.starlife;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan(basePackages = "com.ecl")
@SpringBootApplication
public class StarLifeRpaApiApplication {


	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StarLifeRpaApiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(StarLifeRpaApiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/").allowedOrigins("*");

			}

		};

	}

	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
		return (tomcat) -> tomcat.addConnectorCustomizers((connector) -> {
			if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol) {
				AbstractHttp11Protocol<?> protocolHandler = (AbstractHttp11Protocol<?>) connector
						.getProtocolHandler();
				protocolHandler.setKeepAliveTimeout(80000);
				protocolHandler.setMaxKeepAliveRequests(500);
				protocolHandler.setUseKeepAliveResponseHeader(true);
			}
		});
	}



}
