package com.example.demo_may;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication	//It is the entry point of any springboot application
//It is combination of three annotation @SpringBootConfiguration, @EnableAutoCOnfiguration, @ComponentScan
//Where @SpringBootConfiguration is similar to @Configuration, @EnableAutoConfiguration enables SpringBoot's Auto configuration feature
//@ComponentScan scan for Spring Component in the package and subpackage
public class DemoMayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMayApplication.class, args);
	}

}
