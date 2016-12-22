package com.rz.core.practice.linq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rz.core.practice.model.MonitorDto;

public class LinqHelper {
	public static void main(String[] args) {
		Test();

		System.out.println("Start...");
	}

	public static void Test() {
		// array to list
		String[] values1 = new String[10];
		List<String> values2 = Arrays.asList(values1);
		// array to list
		values1 = values2.toArray(new String[values2.size()]);

		List<MonitorDto> monitorDtos = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			MonitorDto monitorDto = new MonitorDto();
			monitorDto.setAge(i);
			monitorDto.setName("name" + String.valueOf(i));
			monitorDtos.add(monitorDto);
		}

		// Where = filter, Select = map

		// filter
		monitorDtos = monitorDtos.stream().filter(o -> MonitorDto.class == o.getClass()).collect(Collectors.toList());

		// any
		System.out.println(monitorDtos.stream().findAny().isPresent());

		// first
		Optional<MonitorDto> optionalMonitorDto = monitorDtos.stream().filter(o -> 0 == o.getAge()).findFirst();
		if (true == optionalMonitorDto.isPresent()) {
			System.out.println("Not Null");
		} else {
			System.out.println("Null");
		}
		MonitorDto monitorDto = new MonitorDto();
		monitorDto.setAge(12222222);
		monitorDto.setName("12222222");
		// if null, than use monitorDto
		System.out.println(optionalMonitorDto.orElse(monitorDto).toString());
		System.out.println(optionalMonitorDto.orElse(null));

		// map
		List<String> names = monitorDtos.stream().map(o -> o.getName()).collect(Collectors.toList());
		System.out.println(names.toString());
	}
}
