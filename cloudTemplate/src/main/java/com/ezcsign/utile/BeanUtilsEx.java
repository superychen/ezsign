package com.ezcsign.utile;


import net.sf.cglib.beans.BeanCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 采用cglib的BeanCopier替代传统的反射BeanUtils，提升效率
 *
 * @author icechen1219
 * @date 2018/11/17
 */
public class BeanUtilsEx {
    /**
     * 静态缓存，提升BeanCopier对象的创建效率
     */
    private static final Map<String, BeanCopier> BEAN_COPIER_MAP = new HashMap<>();
    private static final Map<String, Method> METHOD_MAP = new HashMap<>();
    private static Logger LOGGER = LoggerFactory.getLogger(BeanUtilsEx.class);

    /**
     * 对象属性复制
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        if (source == null || target == null) {
            throw new RuntimeException("参数不能为null！");
        }
        String beanKey = generateKey(source.getClass(), target.getClass());

        BeanCopier copier;

        if (!BEAN_COPIER_MAP.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            BEAN_COPIER_MAP.put(beanKey, copier);
        } else {
            copier = BEAN_COPIER_MAP.get(beanKey);
        }

        copier.copy(source, target, null);

    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }

    /**
     * 克隆pojo，要求两个对象具备同一类型
     *
     * @param from 源对象
     * @param to   目标对象
     * @throws Exception
     */
    public static void deepClonePojo(Object from, Object to) throws Exception {
        if (from == null || to == null) {
            throw new RuntimeException("要求两个参数必须都不为null！");
        }
        if (!from.getClass().equals(to.getClass())) {
            throw new RuntimeException("要求两个参数必须是同一个类的对象！");
        }
        Class<?> fromClass = from.getClass();
        Field[] fields = fromClass.getDeclaredFields();
        for (Field prop : fields) {
            Method getMethod = guessGetMethod(fromClass, prop);
            Objects.requireNonNull(getMethod).setAccessible(true);
            Object value = getMethod.invoke(from);
            if (value != null) {
                Method setMethod = guessSetMethod(fromClass, prop);
                Objects.requireNonNull(setMethod).setAccessible(true);
                setMethod.invoke(to, value);
            }

        }
    }

    /**
     * 简单拷贝对象属性，不要求对象类型一致，但具备相同的getter/setter
     *
     * @param from
     * @param to
     * @throws Exception
     */
    public static void simpleClonePojo(Object from, Object to) throws Exception {
        if (from == null || to == null) {
            throw new RuntimeException("要求两个参数必须都不为null！");
        }
        Class<?> toClass = to.getClass();
        Field[] fields = toClass.getDeclaredFields();
        for (Field prop : fields) {
            Method getMethod = guessGetMethod(from.getClass(), prop);
            if (getMethod == null) {
                continue;
            }
            getMethod.setAccessible(true);
            Object value = getMethod.invoke(from);
            if (value != null) {
                Method setMethod = guessSetMethod(toClass, prop);
                if (setMethod == null) {
                    continue;
                }
                setMethod.setAccessible(true);
                setMethod.invoke(to, value);
            }

        }
    }

    /**
     * 获取getter方法
     *
     * @param entityClass
     * @param field
     * @return
     */
    public static Method guessGetMethod(Class<?> entityClass, Field field) {
        String prefix = "get";
        if (boolean.class.equals(field.getType()) || Boolean.class.equals(field.getType())) {
            prefix = "is";
        }
        String methodName = prefix + StringUtil.upperFirstChar(field.getName());

        try {
            return entityClass.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            methodName = prefix + field.getName();
            try {
                return entityClass.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException | SecurityException e1) {
                LOGGER.error(e1.getMessage(), e1);
            }
        } catch (SecurityException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 获取setter方法
     *
     * @param entityClass
     * @param field
     * @return
     */
    public static Method guessSetMethod(Class<?> entityClass, Field field) {
        String methodName = "set" + StringUtil.upperFirstChar(field.getName());

        try {
            return entityClass.getDeclaredMethod(methodName, field.getType());
        } catch (NoSuchMethodException e) {
            methodName = "set" + field.getName();
            try {
                return entityClass.getDeclaredMethod(methodName, field.getType());
            } catch (NoSuchMethodException | SecurityException e1) {
                LOGGER.error(e.getMessage(), e);
            }
        } catch (SecurityException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

}
