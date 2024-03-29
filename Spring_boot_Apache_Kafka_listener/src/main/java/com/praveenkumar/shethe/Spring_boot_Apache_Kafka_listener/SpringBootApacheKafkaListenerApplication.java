package com.praveenkumar.shethe.Spring_boot_Apache_Kafka_listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringBootApacheKafkaListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApacheKafkaListenerApplication.class, args);
	}

}
