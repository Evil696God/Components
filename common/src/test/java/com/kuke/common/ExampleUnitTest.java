package com.kuke.common;

import android.os.Handler;

import com.kuke.common.network.ApiResponse;
import com.kuke.common.network.LoginResult;
import com.kuke.common.network.Network;
import com.kuke.common.utils.GsonUtils;

import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

import io.reactivex.functions.Consumer;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
       new Handler().sendEmptyMessage(1);
    }

    @Test
    public void login() {
        HashMap<String, String> map = new HashMap<>();
        map.put("account", "ctest1");
        map.put("password", "e10adc3949ba59abbe56e057f20f883e");
//        map.put("uuid", SPUtil.getInstance().getUUID());
        map.put("uuid", "867400020316612");
        System.out.println("LoginResultMap:" + GsonUtils.toJsons(map));
        Network.getHttpApi().login(map).subscribe(
                new Consumer<ApiResponse<LoginResult>>() {
                    @Override
                    public void accept(ApiResponse<LoginResult> s) throws Exception {
                        System.out.println("LoginResult:" + GsonUtils.toJsons(s));
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("LoginOnError:" + throwable.getMessage());
                    }
                }
        );
    }
}