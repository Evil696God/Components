

package com.kuke.common.utils.websocketutil;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.kuke.common.utils.LogManagementUtils;

import androidx.annotation.RequiresPermission;


/**
 * @author John.Gu
 * @data 2019/1/30
 * @email guqinghong@beyondsoft.com
 * @remark 监听网络变化广播，网络变化时自动重连
 */
public class NetworkChangedReceiver extends BroadcastReceiver {

    private static final String TAG = "WebSocket:";

    private WebSocketService socketService;

    public NetworkChangedReceiver() {
    }

    public NetworkChangedReceiver(WebSocketService socketService) {
        this.socketService = socketService;
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager == null) return;
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            if (activeNetwork != null) {
                if (activeNetwork.isConnected()) {
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        LogManagementUtils.showLog(TAG +"网络连接发生变化，当前WiFi连接可用，正在尝试重连。");
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        LogManagementUtils.showLog(TAG+ "网络连接发生变化，当前移动连接可用，正在尝试重连。");
                    }
                    if (socketService != null) {
                        socketService.reconnect();
                    }
                } else {
                    LogManagementUtils.showLog(TAG+"当前没有可用网络");
                }
            }
        }
    }
}
