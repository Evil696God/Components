

package com.kuke.common.network;

import com.google.gson.Gson;
import com.kuke.common.base.basebean.BaseBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import okhttp3.RequestBody;

/**
 * RequestBody封装工具类
 *
 * @date: 2020-03-31
 * @version: 1.0
 * @author: wangchenxing
 */
public class RequestBodyGenerateUtils {
    public static Gson gson;

    public static RequestBody getRequestBody(BaseBean bean) {
        if (gson == null) {
            gson = new Gson();
        }
        String json = gson.toJson(bean);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
    }

    @Nullable
    public static RequestBody getRequestBody(@NotNull List<BaseBean> data) {
        if (gson == null) {
            gson = new Gson();
        }
        String json = gson.toJson(data);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
    }
}
