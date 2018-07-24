package com.github.mthizo247.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.github.mthizo247.cloud.netflix.zuul.web.socket.EnableZuulWebSocket;

@SpringBootApplication
@EnableZuulWebSocket
@EnableDiscoveryClient
public class ProxyApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}
}
