package com.rz.core.practice.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import com.rz.core.practice.model.MonitorDto;
import com.rz.core.practice.model.TagAnnotation;

public class Helper {
    public static void main(String[] args){
        Helper helper = new Helper();
        
        try {
            helper.testClassLoader();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("End Helper");
    }
    
	private void reflectTest() throws Exception {
		MonitorDto monitorDto = new MonitorDto();
		monitorDto.setName("adasdasd");
		// System.out.println(monitorDto.getName());

		// Class<?> classclassType = monitorDto.getClass();
		Class<MonitorDto> classclassType = MonitorDto.class;

		Field[] fields = classclassType.getDeclaredFields();
		for (Field field : fields) {
			// Annotation[] annotations = classclassType.getAnnotations();
			// System.out.println(annotations.length);
			// for(Annotation annotation : annotations){
			// System.out.println(annotation.annotationclassType().getName());
			// }

			TagAnnotation tagAnnotation = field.getAnnotation(TagAnnotation.class);
			if (null != tagAnnotation) {
				System.out.println(tagAnnotation.value());
			}
			System.out.println(field.getName());
		}

		Method[] methods = classclassType.getMethods();
		for (Method method : methods) {
			if (true == "setName".equalsIgnoreCase(method.getName())) {
				method.invoke(monitorDto, "222222");
			} else if (true == "getName".equalsIgnoreCase(method.getName())) {
				System.out.println(method.invoke(monitorDto).toString());
			}
		}

		System.out.println(monitorDto.getName());
	}

	private void throwException() throws Exception {
		Thread.sleep(1000);
		throw new Exception("aasdasd");
	}

	private void lambdaTest(Function<String, Integer> callBack) {
		List<String> list1 = Collections.synchronizedList(new ArrayList<String>());
		List<String> list2 = new ArrayList<String>();
		Map<String, Integer> map1 = new ConcurrentHashMap<String, Integer>();
		Map<String, Integer> map2 = Collections.synchronizedMap(new HashMap<String, Integer>());
		Map<String, Integer> map3 = new HashMap<String, Integer>();
		Queue<String> queue1 = new ConcurrentLinkedQueue<String>();
		Queue<String> queue2 = new LinkedList<String>();

		Consumer<String> asd = o -> {
			
		};
		int re = callBack.apply("asd");

		List<MonitorDto> monitorDtos = new ArrayList<MonitorDto>();
		// System.out.println(monitorDtos.size());
		// monitorDtos.forEach
		MonitorDto monitorDto = new MonitorDto();
		monitorDto.setName("name2");
		monitorDtos.add(monitorDto);
		monitorDto = new MonitorDto();
		monitorDto.setName("name1");
		monitorDtos.add(monitorDto);
		monitorDto = new MonitorDto();
		monitorDto.setName("name3");
		monitorDtos.add(monitorDto);
		// monitorDtos.forEach(o->System.out.println(o));
		Stream<MonitorDto> streamMonitorDtos = monitorDtos.stream();
		streamMonitorDtos = streamMonitorDtos/* .parallel() */.filter(o -> o.getName().startsWith("name"));
		// streamMonitorDtos.filter(o->o.getName().endsWith("1"));
		streamMonitorDtos = streamMonitorDtos.sorted(new Comparator<MonitorDto>() {
			@Override
			public int compare(MonitorDto arg0, MonitorDto arg1) {
				return arg0.getName().compareTo(arg1.getName());
			}
		});
		streamMonitorDtos = streamMonitorDtos.sorted((o0, o1) -> o0.getName().compareTo(o1.getName()));

		// Stream<Object> mapDtos = streamMonitorDtos.map(o -> {
		// MapDto mapDto = new MapDto();
		// mapDto.setEnglishName("English_" + o.getName());
		// mapDto.setResult(0 == o.getAge() % 2);
		// return mapDto;
		// });
		// mapDtos.forEach(o -> System.out.println(((MapDto)
		// o).getEnglishName()));

		System.out.println("monitorDtos size: " + monitorDtos.size());
	}

	private Object convertTest(Class<?> classType, String value) throws Exception {
		if (null == classType) {
			throw new Exception("classType");
		}
		if (null == value) {
			throw new Exception("value");
		}

		if (String.class == classType) {
			return value;
		} else if (Integer.class == classType) {
			return Integer.parseInt(value);
		} else if (Boolean.class == classType) {
			return Boolean.parseBoolean(value);
		} else if (Long.class == classType) {
			return Long.parseLong(value);
		} else if (Date.class == classType) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
			return simpleDateFormat.parse(value);
		}
		// else if (true == classType.isEnum())
		// {
		// return Enum.valueOf(classType, value);
		// }
		else if (BigDecimal.class == classType) {
			return new BigDecimal(value);
		} else if (Character.class == classType) {
			return Character.valueOf(value.charAt(0));
		} else if (Short.class == classType) {
			return Short.parseShort(value);
		} else if (Byte.class == classType) {
			return Byte.parseByte(value);
		} else if (Float.class == classType) {
			return Float.parseFloat(value);
		} else if (Double.class == classType) {
			return Double.parseDouble(value);
		} else if (Byte[].class == classType) {
			return value.getBytes("UTF-8");
		} else {
			throw new Exception("NotSupported");
		}
	}

	private void threadTest() {
		ExecutorService service = Executors.newSingleThreadExecutor();
		service.submit(() -> {

		});

		new Thread(() -> {

		}).start();
	}

	private <T extends Enum<T>> T getEnumFromString(Class<T> classType, String value) {
		if (null != classType && null != value) {
			try {
				return Enum.valueOf(classType, value.trim().toUpperCase());
			} catch (IllegalArgumentException ex) {

			}
		}
		return null;
	}
	
    
    // full access
	public void testPublicScope(){
        System.out.println("public");
    }
    
    // access for same and sub package path
    void testNothingScope(){
        System.out.println("nothing");
    }
    
    private ClassLoader testClassLoader() throws ClassNotFoundException{
        //return ClassUtils.getDefaultClassLoader();
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(".");
        System.out.println(url);
        
        Class<?> clazz = classLoader.loadClass("com.rz.core.practice.io.FileHelper");
        System.out.println(clazz.toString());
        
        return classLoader;
    }
}