package com.fantasy.football;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;


@EnableCaching
@ComponentScan(basePackages = { "com.fantasy.football" })
//@EntityScan(basePackages = "com.fantasy.football.domain")
//@EnableJpaRepositories(basePackages = "com.fantasy.football.repository")
//@EnableAut																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																						oConfiguration
//@EnableJpaAuditing
@SpringBootApplication
public class ServerFfApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerFfApplication.class, args);
	}

}
