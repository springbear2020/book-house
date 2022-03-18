package com.bear.bookhouse.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author Spring-_-Bear
 * @datetime 2022/3/16 19:42
 */
public class WebUtil {
    /**
     * 将客户端表单的请求参数转换为对应 JavaBean
     *
     * @param bean  目标 JavaBean 对象
     * @param value 客户端请求参数
     * @return java bean or null
     */
    public static <T> T copyParamsToBean(T bean, Map<String, String[]> value) {
        try {
            org.apache.commons.beanutils.BeanUtils.populate(bean, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

}
