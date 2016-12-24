package com.rz.core;

import java.util.List;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CloneMachine {
    private static Map<Class<?>, Field[]> fields;

    static {
        CloneMachine.fields = new ConcurrentHashMap<>();
    }

    public static <T> T clone(T instance) throws Exception {
        List<Object> instanceRecords = new ArrayList<>();
        return CloneMachine.clone(instance, instanceRecords, true);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static <T> T clone(T instance, List<Object> instanceRecords, boolean ignoreException) throws Exception {
        if (null == instance) {
            return null;
        }

        // not allow loop reference the same instance
        // throw StackOverflowError when invoke .hashCode() with by instance
        // that has loop reference
        if (true == instanceRecords.stream().filter(o -> true == instance.equals(o)).findAny().isPresent()) {
            return instance;
        } else {
            instanceRecords.add(instance);
        }

        Class<?> clazz = instance.getClass();

        if (true == RZHelper.isBaseClazz(clazz)) {
            return instance;
        }

        T newInstance = null;
        int length = 0;
        try {
            if (true == clazz.isArray()) {
                length = Array.getLength(instance);
                newInstance = (T) Array.newInstance(instance.getClass().getComponentType(), length);
            } else {
                newInstance = (T) clazz.newInstance();
            }
        } catch (Exception e) {
            if (true == ignoreException) {
                System.out.println(e.getMessage());
            } else {
                throw e;
            }

            return instance;
        }

        if (true == clazz.isArray()) {
            for (int i = 0; i < length; i++) {
                Object item = Array.get(instance, i);
                Object newItem = item;
                try {
                    newItem = CloneMachine.clone(item, instanceRecords, ignoreException);
                } catch (Exception e) {
                    if (true == ignoreException) {
                        System.out.println(e.getMessage());
                    } else {
                        throw e;
                    }
                }
                Array.set(newInstance, i, newItem);
            }
        } else if (instance instanceof List) {
            List list = (List) instance;
            for (Object item : list) {
                Object newItem = item;
                try {
                    newItem = CloneMachine.clone(item, instanceRecords, ignoreException);
                } catch (Exception e) {
                    if (true == ignoreException) {
                        System.out.println(e.getMessage());
                    } else {
                        throw e;
                    }
                }
                ((List) newInstance).add(newItem);
            }
        } else if (instance instanceof Map) {
            Map map = (Map) instance;

            for (Object item : map.entrySet()) {
                Map.Entry entiy = (Map.Entry) item;

                Object entiyKey = entiy.getKey();
                Object newEntiyKey = entiyKey;
                try {
                    newEntiyKey = CloneMachine.clone(entiyKey, instanceRecords, ignoreException);
                } catch (Exception e) {
                    if (true == ignoreException) {
                        System.out.println(e.getMessage());
                    } else {
                        throw e;
                    }
                }

                Object entiyValue = entiy.getValue();
                Object newEntiyValue = entiyValue;
                try {
                    newEntiyValue = CloneMachine.clone(entiyValue, instanceRecords, ignoreException);
                } catch (Exception e) {
                    if (true == ignoreException) {
                        System.out.println(e.getMessage());
                    } else {
                        throw e;
                    }
                }

                ((Map) newInstance).put(newEntiyKey, newEntiyValue);
            }
        } else {
            Field[] fields = null;
            if (false == CloneMachine.fields.containsKey(clazz)) {
                fields = clazz.getDeclaredFields();
                CloneMachine.fields.put(clazz, fields);
            }
            fields = CloneMachine.fields.get(clazz);
            for (Field field : fields) {
                field.setAccessible(true);

                // TODO Annotation check
                // Annotation[] annotations= field.getAnnotations();

                Object fieldValue = field.get(instance);
                Object newFieldValue = fieldValue;
                try {
                    newFieldValue = CloneMachine.clone(fieldValue, instanceRecords, ignoreException);
                } catch (Exception e) {
                    if (true == ignoreException) {
                        System.out.println(e.getMessage());
                    } else {
                        throw e;
                    }
                }

                field.set(newInstance, newFieldValue);
            }
        }

        return newInstance;
    }
}
