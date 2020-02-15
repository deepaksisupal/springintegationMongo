package com.mongosi.demo;

import com.mongosi.demo.configuration.InfrastructureConfiguration;
import com.mongosi.demo.entity.PosLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		new DemoApplication().start(context);
	}

	public void start(ConfigurableApplicationContext context) {

		PosLog pos1 = new PosLog("Shika", "London", 78, true);
		PosLog pos2 = new PosLog("Michael", "Uxbridge", 34, false);
		PosLog pos3 = new PosLog("Sree", "Uk", 78, true);
		InfrastructureConfiguration.MongoService mongoService = context.getBean(InfrastructureConfiguration.MongoService.class);
		mongoService.order(pos1);
		mongoService.order(pos2);
		mongoService.order(pos3);
	}
}
