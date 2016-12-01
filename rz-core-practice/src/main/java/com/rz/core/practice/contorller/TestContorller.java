package com.rz.core.practice.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rz.core.practice.service.ITestService;

@RestController
@Scope("prototype")
public class TestContorller {
	@Autowired
	private ITestService testService;
	
	@RequestMapping(value = "/hello/{someone}") // http://localhost:8080/hello/abc
	public String hello(@PathVariable("someone") String someone) {
		return testService.Test("value");
	}
}
