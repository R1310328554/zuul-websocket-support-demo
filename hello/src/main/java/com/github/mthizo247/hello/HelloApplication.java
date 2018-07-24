package com.github.mthizo247.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
public class HelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

	@Autowired
	SimpMessagingTemplate messagingTemplate;

//	@Scheduled(fixedDelay = 1000L)
//	public void time() {
//		Greeting greeting = new Greeting(new Date().toString());
//		messagingTemplate.convertAndSend("/topic/time", greeting);
////		messagingTemplate.convertAndSendToUser();
//	}
}
