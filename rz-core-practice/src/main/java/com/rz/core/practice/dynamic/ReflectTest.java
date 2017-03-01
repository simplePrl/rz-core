package com.rz.core.practice.dynamic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.rz.core.practice.model.MonitorDto;
import com.rz.core.practice.model.TagAnnotation;

public class ReflectTest {
    public static void main(String[] args) {
        ReflectTest reflectTest = new ReflectTest();
        try {
            reflectTest.test();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("End ReflectTest...");
    }

    void test() throws Exception {
        Class<MonitorDto> clazz = MonitorDto.class;
        System.out.println("Class SimpleName: " + clazz.getSimpleName());
        System.out.println("Class Name: " + clazz.getCanonicalName());
        System.out.println("Class CanonicalName: " + clazz.getCanonicalName());
        System.out.println("Class TypeName: " + clazz.getTypeName());
        // new instance
        MonitorDto monitorDto = (MonitorDto) clazz.newInstance();
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("constructor: " + constructor.toString());
        }
        Constructor<?> constructor = clazz.getConstructor(boolean.class);
        monitorDto = (MonitorDto) constructor.newInstance(false);

        // get class
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> klazz = monitorDto.getClass();
        klazz = Class.forName("com.rz.core.practice.model.MonitorDto");
        klazz = classLoader.loadClass("com.rz.core.practice.model.MonitorDto");

        // filed
        Field[] fields = clazz.getFields();
        fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            TagAnnotation tagAnnotation = field.getAnnotation(TagAnnotation.class);
            if (null != tagAnnotation) {
                System.out.println("annotation: " + tagAnnotation.value());
            }
            System.out.println("field: " + field.getName());
            int modifiers = field.getModifiers();
            if (!Modifier.isPublic(modifiers)) {
                field.setAccessible(true);
            }
            if (Modifier.isStatic(modifiers)) {
                System.out.println("----value:" + field.get(null));
            } else {
                System.out.println("----value:" + field.get(monitorDto));
            }
        }
        Field field = klazz.getDeclaredField("name");
        field.setAccessible(true);
        field.set(monitorDto, "updateeeee");

        // method
        Method[] methods = klazz.getMethods();
        methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("method: " + method.toString());
        }
        Method method = klazz.getDeclaredMethod("run", new Class<?>[] { boolean.class });
        method.setAccessible(true);
        int modifiers = method.getModifiers();
        if (Modifier.isStatic(modifiers)) {
            System.out.println(method.invoke(null, new Object[] { false }));
        } else {
            System.out.println("method invoke: " + method.invoke(monitorDto, new Object[] { false }));
        }

        // extends
        System.out.println("Superclass: " + klazz.getSuperclass().getName());
        Class<?>[] instanceClasses = klazz.getInterfaces();
        for (Class<?> instanceClass : instanceClasses) {
            System.out.println("instanceClass: " + instanceClass.getName());
        }

        // utils
        System.out.println(klazz.getPackage().toString());

        System.out.println(monitorDto);
    }
}
