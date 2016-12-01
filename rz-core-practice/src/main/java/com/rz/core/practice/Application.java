package com.rz.core.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rz.core.practice.elasticsearch.ElasticsearchHelper;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		//ElasticsearchHelper.Test();
		// TODO Auto-generated method stub
		System.out.println("start...");
		
		SpringApplication.run(Application.class, args);
	}

}
