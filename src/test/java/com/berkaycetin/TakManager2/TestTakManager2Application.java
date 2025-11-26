package com.berkaycetin.TakManager2;

import org.springframework.boot.SpringApplication;

import com.berkaycetin.starter.TakManager2Application;

public class TestTakManager2Application {

	public static void main(String[] args) {
		SpringApplication.from(TakManager2Application::main).with(TestcontainersConfiguration.class).run(args);
	}

}
