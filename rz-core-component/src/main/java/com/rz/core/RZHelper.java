package com.rz.core;

import java.io.Closeable;
import java.util.Collection;
import java.util.Map;

public class RZHelper {
    public static boolean isBaseClazz(Class<?> clazz) {
        if (null == clazz) {
            new IllegalAccessException("clazz");
        }

        if (true == clazz.isPrimitive() || String.class == clazz || Integer.class == clazz || Boolean.class == clazz || Character.class == clazz || Byte.class == clazz || Short.class == clazz
                || Long.class == clazz || Float.class == clazz || Double.class == clazz || Void.class == clazz) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean interfaceOf(Class<?> clazz, Class<?> interfaceClazz) {
        if (null == clazz) {
            new IllegalAccessException("clazz");
        }

        Class<?>[] interfaceClazzes = clazz.getInterfaces();
        for (int i = 0; i < interfaceClazzes.length; i++) {
            if (interfaceClazz == interfaceClazzes[i]) {
                return true;
            }
        }

        return false;
    }

    public static boolean isEmptyCollection(Collection<?> collection) {
        if (null == collection || true == collection.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmptyCollection(Map<?, ?> map) {
        if (null == map || true == map.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static void SafeDispose(Closeable instance) {
        Assert.isNotNull(instance, "instance");

        try {
            instance.close();
        } catch (Exception exception) {
            //ServiceLocator.Logger.Error(exception, "Failed to dispose the instance.");
        }
    }
}
