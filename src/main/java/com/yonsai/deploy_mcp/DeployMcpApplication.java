package com.yonsai.deploy_mcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients 
@SpringBootApplication
public class DeployMcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeployMcpApplication.class, args);
	}

}
