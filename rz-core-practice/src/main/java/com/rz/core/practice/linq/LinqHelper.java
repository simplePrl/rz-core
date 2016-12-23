package com.rz.core.practice.linq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.rz.core.practice.model.MonitorDto;

public class LinqHelper {
    public static void main(String[] args) {
        LinqHelper linqHelper = new LinqHelper();
        linqHelper.test();

        System.out.println("End LinqHelper...");
    }

    public void test() {
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
        // first -- if null, than use monitorDto
        System.out.println(optionalMonitorDto.orElse(monitorDto).toString());
        System.out.println(optionalMonitorDto.orElse(null));
        // first -- throw
        try {
            monitorDtos.stream().findFirst().orElseThrow(() -> {
                return new Exception("throw when no frist");
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // map
        List<String> names = monitorDtos.stream().map(o -> o.getName()).collect(Collectors.toList());
        System.out.println(names.toString());

        // sort
        System.out.println(monitorDtos.stream().sorted((o1, o2) -> o2.getName().compareTo(o1.getName())).collect(Collectors.toList()));
        System.out.println(monitorDtos.stream().sorted((o1, o2) -> NumberUtils.compare(o1.getAge(), o2.getAge())).collect(Collectors.toList()));
    }

    public void issue() throws Exception {
        MonitorDto monitorDto = null;
        Date shardingDate = null;
        List<Date> dates = new ArrayList<Date>();
        for (Date date : dates) {
            List<MonitorDto> monitorDtos = null;
            try {
                monitorDtos = Arrays.asList(new MonitorDto(), new MonitorDto(), new MonitorDto());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (null != monitorDtos && true == monitorDtos.stream().findFirst().isPresent()) {
                monitorDto = monitorDtos.stream().sorted((o1, o2) -> NumberUtils.compare(o2.getAge(), o1.getAge())).findFirst().orElseThrow(null); // step 1
                shardingDate = date;
                break;
            }
        }
        if (null == monitorDto) {
            throw new Exception();
        }

        String name = monitorDto.getName();
        final MonitorDto finalMonitorDto = monitorDto; // same to [MonitorDto finalMonitorDto = monitorDto]
        List<String> appInfos = Arrays.asList("111", "222", "333");
        String appInfoName = null;
        
        // appInfoName = appInfos.stream().filter(o -> true == StringUtils.equals(monitorDto.getName(), o)).findFirst().orElse(null); // false: monitorDto is not final, cause set value to it at step 1
        appInfoName = appInfos.stream().filter(o -> true == StringUtils.equals(finalMonitorDto.getName(), o)).findFirst().orElse(null); // true: finalMonitorDto is final
        appInfoName = appInfos.stream().filter(o -> true == StringUtils.equals(name, o)).findFirst().orElse(null); // true: name is final
        System.out.println(shardingDate.toString() + appInfoName + name);
    }
}
