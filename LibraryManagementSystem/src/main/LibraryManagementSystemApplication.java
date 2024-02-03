package com.hillogy.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.hillogy.library.initial.InitialLoad;

@SpringBootApplication
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LibraryManagementSystemApplication.class, args);
        context.getBean(InitialLoad.class).load();
	}

}
