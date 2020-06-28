package com.kuke.common.network;

import com.kuke.common.value.ExceptionValue;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 异常状态监控器
 *
 * @date: 2020-03-31
 * @version: 1.0
 * @author: wangchenxing
 */
public class ExceptionMonitoringInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response = chain.proceed(chain.request());
        if (response.code() == ExceptionValue.SUCCESS) {

        } else {

        }
        return response;
    }
}
