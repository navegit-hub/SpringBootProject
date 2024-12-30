package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavajdbcprojectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JavajdbcprojectApplication.class, args);
		
		System.out.println("I'm java spring boot");
		
		ServiceClass obj = context.getBean(ServiceClass.class);
		obj.test();
	}

}
