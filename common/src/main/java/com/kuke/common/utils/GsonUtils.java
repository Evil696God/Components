

package com.kuke.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Gson字符串转换工具类
 *
 * @date: 2018-12-15
 * @version: 1.0
 * @author: wangchenxing
 */
public class GsonUtils {
    private static Gson gson;

    public static <T> List<T> jsonToListBean(String jsonString, Class<T> cls) {
        if (gson == null) {
            gson = new Gson();
        }

        List<T> list = new ArrayList<T>();
        if (gson != null) {
            JsonArray array = new JsonParser().parse(jsonString).getAsJsonArray();
            for (JsonElement jsonElement : array) {
                list.add(gson.fromJson(jsonElement, cls));
            }
        }
        return list;
    }

    public static <T> T jsonToBean(String jsonString, Class<T> cls) {
        if (gson == null) {
            gson = new Gson();
        }
        T t = null;
        if (gson != null) {
            t = gson.fromJson(jsonString, cls);
        }
        return t;
    }

    public static <T> String toJsons(T t){
        Gson gson = new Gson();
        String s = gson.toJson(t);
        return s;
    }
}
