package com.rz.core.practice;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rz.core.practice.elasticsearch.ElasticsearchHelper;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		//ElasticsearchHelper.Test();
		
		//AnnotationUtils
		//AopUtils just for spring
		//ReflectionUtils
		
		String uuid = UUID.randomUUID().toString();
		System.out.println(uuid);
		
		String asd= null;
		String dsa = "asdasd" + asd;
		System.out.println(dsa);
		
		String[] flagSplit = "ssss,2222,4444,dddd".split(",");
		System.out.println(flagSplit[2]);
		
		//SpringApplication.run(Application.class, args);
	}

}
