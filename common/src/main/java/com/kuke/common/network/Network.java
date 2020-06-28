

package com.kuke.common.network;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit工具类
 *
 * @date: 2020-03-31
 * @version: 1.0
 * @author: wangchenxing
 */
public class Network {
    private static volatile Api fnbApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();
    // 网络连接超时时间
    public static int NETWORK_CONNECT_TIME_OUT = 10;
    public static int NETWORK_READ_TIME_OUT = 10 * 60;
    public static int NETWORK_WRITE_TIME_OUT = 5 * 60;

    public static Api getHttpApi() {
        if (fnbApi == null) {
            synchronized (Api.class) {
                if (fnbApi == null) {
                    buildOkHttp();
                    Retrofit retrofit = new Retrofit.Builder()
                            .client(okHttpClient)
                            .baseUrl(Url.BASE_URL)
                            .addConverterFactory(gsonConverterFactory)
                            .addCallAdapterFactory(rxJava2CallAdapterFactory)
                            .build();
                    fnbApi = retrofit.create(Api.class);
                }
            }
        }

        return Network.fnbApi;
    }

    /**
     * 配置okHttp
     * 链接超时
     * 添加请求头
     */
    private static void buildOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).retryOnConnectionFailure(true);
        builder.addInterceptor(new ExceptionMonitoringInterceptor());
        okHttpClient = builder.build();
    }

}
