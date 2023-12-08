package com.game.monopolydeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication( exclude = { SecurityAutoConfiguration.class } )
@PropertySource("classpath:application.properties")
public class MonopolydealApplication {



	public static void main(String... args) {
		SpringApplication application = new SpringApplication(MonopolydealApplication.class);
		application.setAdditionalProfiles("ssl");
		application.run(args);
	}
}