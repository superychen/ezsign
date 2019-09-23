package com.ezcsign.comm;

import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanMap;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Slf4j
public class JavaBeanUtil {
     /**
     * 实体类转map
     * @param obj
     * @return
     */
     public static Map<String, Object>  convertBeanToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,property.getName());
                // 过滤class属性
                if (!key.equals("class")) {
                // 得到property对应的getter方法
                Method getter = property.getReadMethod();
                Object value = getter.invoke(obj);
                    if(null == value){
                        map.put(key,"");
                    }else{
                        map.put(key,value);
                    }
                }
            }
        } catch (Exception e) {
            log.error("convertBean2Map Error {}" ,e);
        }

         Set<String> set = map.keySet();
         Iterator<String> it = set.iterator();
         while (it.hasNext()) {
            String str = it.next();
            if(map.get(str)==null || map.get(str) ==""){
                map.remove(str);
                set = map.keySet();
                it = set.iterator();
            }
         }
         log.debug("转换为的map-->{}",map);
         return map;
        }



    /**
     * 将map装换为javabean对象
     *
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

}
