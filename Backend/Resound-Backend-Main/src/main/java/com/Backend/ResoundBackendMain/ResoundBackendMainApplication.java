package com.Backend.ResoundBackendMain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class ResoundBackendMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResoundBackendMainApplication.class, args);
	}

}
