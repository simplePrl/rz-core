package com.rz.core.practice.linq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.rz.core.practice.model.MonitorDto;

public class LinqHelper {
	public static void main(String[] args) {
		Test();

		System.out.println("Start...");
	}

	public static void Test() {
		List<MonitorDto> monitorDtos = new ArrayList<>();
		for (int i = 0; i < 0; i++) {
			MonitorDto monitorDto = new MonitorDto();
			monitorDto.setAge(i);
			monitorDto.setName("name" + String.valueOf(i));
			monitorDtos.add(monitorDto);
		}
		
		// Where = filter, Select = map

		// first
		Stream<MonitorDto> streamMonitorDtos = monitorDtos.stream();
		Optional<MonitorDto> optionalMonitorDto = streamMonitorDtos.filter(o -> 0 == o.getAge()).findFirst();
		if (true == optionalMonitorDto.isPresent()) {
			System.out.println("Not Null");
		} else {
			System.out.println("Null");
		}		
		MonitorDto monitorDto = new MonitorDto();
		monitorDto.setAge(12222222);;
		monitorDto.setName("12222222");
		System.out.println(optionalMonitorDto.orElse(monitorDto).toString()); // if null, than use monitorDto
		System.out.println(optionalMonitorDto.orElse(null));
	}
}
