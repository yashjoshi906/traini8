package co.traini8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.datafaker.Faker;

@Configuration
public class AppConfig {

	@Bean
	public Faker faker() {
		return new Faker();
	}

}