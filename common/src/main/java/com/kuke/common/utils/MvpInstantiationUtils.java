

package com.kuke.common.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * MVP绑定工具类
 *
 * @date: 2018-11-30
 * @version: 1.0
 * @author: wangchenxing
 */
public class MvpInstantiationUtils {

    public static final int FIRSTGENERICITY = 0;

    /**
     * 重要说明:生成对象必须有无参构造器!!
     */
    public static <T> T getInstantiation(Object object, int i) {
        try {
            Class<?> aClass = object.getClass();
            Type type = aClass.getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class<T> tClass = null;
            if (parameterizedType != null) {
                tClass = (Class<T>) parameterizedType.getActualTypeArguments()[i];
            }
            return tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

}
