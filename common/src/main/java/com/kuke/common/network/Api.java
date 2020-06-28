

package com.kuke.common.network;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * API接口调用
 *
 * @version 1.0
 * @date: 2020-03-31
 * @author: wangchenxing
 */
public interface Api {
    @FormUrlEncoded
    @POST("api/v1/user/login/info")
    Observable<ApiResponse<LoginResult>> login(@FieldMap Map<String, String> map);
}
