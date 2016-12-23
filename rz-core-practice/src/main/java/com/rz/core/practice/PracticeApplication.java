package com.rz.core.practice;

import java.lang.annotation.Annotation;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.rz.core.practice.config.PracticeConfig;
import com.rz.core.practice.dynamic.AspectWork;
import com.rz.core.practice.dynamic.Worker;
import com.rz.core.utils.DateTimeUtils;

@SpringBootApplication
//@ComponentScan({ "com.hujiang.basic.framework.rest", "com.hujiang.basic.framework.plugin.cache", "com.hujiang.notifycenter.apppush", "com.hujiang.notifycenter.core" })
//@Import({PracticeConfig.class})
public class PracticeApplication {

	public static void main(String[] args) throws Exception {
		
		//System.out.println(DateTimeUtils.addYear(null, 100));
		
		//ElasticsearchHelper.Test();
		
		//AnnotationUtils
		//AopUtils just for spring
		//ReflectionUtils
		
//		String uuid = UUID.randomUUID().toString();
//		System.out.println(uuid);
//		
//		String asd= null;
//		String dsa = "asdasd" + asdApplicationContext;
//		System.out.println(dsa);
//		
//		String[] flagSplit = "ssss,2222,4444,dddd".split(",");
//		System.out.println(flagSplit[2]);
		
		//SpringApplication.run(PracticeApplication.class, args);
		
		System.out.println("End Application...");
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PracticeConfig.class);
//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//		applicationContext.register(PracticeConfig.class);
//		applicationContext.refresh();
		
//		System.out.println(StringUtils.join(applicationContext.getBeanDefinitionNames(), ","));
		AspectWork aspectWork = applicationContext.getBean(AspectWork.class);
//		System.out.println(null == worker);
		
		aspectWork.run(111);
		
		System.out.println();
	}

}
