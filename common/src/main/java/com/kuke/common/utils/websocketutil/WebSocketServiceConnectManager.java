

package com.kuke.common.utils.websocketutil;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import com.kuke.common.utils.LogManagementUtils;

/**
 * @author John.Gu
 * @data 2019/1/30
 * @email guqinghong@beyondsoft.com
 * @remark  负责页面的 WebSocketService 绑定等操作
 */
public class WebSocketServiceConnectManager {

    private static final String TAG = "WebSocket:";

    private Context context;
    private IWebSocketPage webSocketPage;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    /**
     * WebSocket 服务是否绑定成功
     */
    private boolean webSocketServiceBindSuccess = false;
    protected WebSocketService mWebSocketService;

    private int bindTime = 0;
    /**
     * 是否正在绑定服务
     */
    private boolean binding = false;

    protected ServiceConnection mWebSocketServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            webSocketServiceBindSuccess = true;
            binding = false;
            bindTime = 0;
            mWebSocketService = ((WebSocketService.ServiceBinder) service).getService();
            mWebSocketService.addListener(mSocketListener);
            webSocketPage.onServiceBindSuccess();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binding = false;
            webSocketServiceBindSuccess = false;
            LogManagementUtils.showLog(TAG+"onServiceDisconnected:" + name);
            if (bindTime < 5 && !binding) {
                LogManagementUtils.showLog(TAG+ String.format("WebSocketService 连接断开，开始第%s次重连", bindTime));
                bindService();
            }
        }
    };

    private SocketListener mSocketListener = new SocketListener() {
        @Override
        public void onConnected() {
            mHandler.post(() -> webSocketPage.onConnected());
        }

        @Override
        public void onConnectError(final Throwable cause) {
            mHandler.post(() -> webSocketPage.onConnectError(cause));
        }

        @Override
        public void onDisconnected() {
            mHandler.post(() -> webSocketPage.onDisconnected());
        }

        @Override
        public void onMessageResponse(final ReceiveResponse message) {
            mHandler.post(() -> webSocketPage.onMessageResponse(message));
        }

        @Override
        public void onSendMessageError(final ErrorResponse error) {
            mHandler.post(() -> webSocketPage.onSendMessageError(error));
        }
    };

    public WebSocketServiceConnectManager(Context context, IWebSocketPage webSocketPage) {
        this.context = context;
        this.webSocketPage = webSocketPage;
        webSocketServiceBindSuccess = false;
    }

    public void onCreate() {
        bindService();
    }

    private void bindService() {
        binding = true;
        webSocketServiceBindSuccess = false;
        Intent intent = new Intent(context, WebSocketService.class);
        context.bindService(intent, mWebSocketServiceConnection, Context.BIND_AUTO_CREATE);
        bindTime++;
    }

    /**
     * 向服务器发送 数据
     * @param text
     */
    public void sendText(String text) {
        if (webSocketServiceBindSuccess && mWebSocketService != null) {
            mWebSocketService.sendText(text);
        } else {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorCode(2);
            errorResponse.setCause(new Throwable("WebSocketService dose not bind!"));
            errorResponse.setRequestText(text);
            ResponseDelivery delivery = new ResponseDelivery();
            delivery.addListener(mSocketListener);
            WebSocketSetting.getResponseProcessDelivery().onSendMessageError(errorResponse, delivery);
            if (!binding) {
                bindTime = 0;
                LogManagementUtils.showLog(TAG+ String.format("WebSocketService 连接断开，开始第%s次重连", bindTime));
                bindService();
            }
        }
    }

    /**
     * webSocket 重连
     */
    public void reconnect() {
        if (webSocketServiceBindSuccess && mWebSocketService != null) {
            mWebSocketService.reconnect();
        } else {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorCode(2);
            errorResponse.setCause(new Throwable("WebSocketService dose not bind!"));
            ResponseDelivery delivery = new ResponseDelivery();
            delivery.addListener(mSocketListener);
            WebSocketSetting.getResponseProcessDelivery().onSendMessageError(errorResponse, delivery);
            if (!binding) {
                bindTime = 0;
                LogManagementUtils.showLog(TAG+ String.format("WebSocketService 连接断开，开始第%s次重连", bindTime));
                bindService();
            }
        }
    }

    /**
     * 关闭 webSocket 服务
     */
    public void onDestroy() {
        LogManagementUtils.showLog(TAG+"onDestroy");
        binding = false;
        bindTime = 0;

        context.unbindService(mWebSocketServiceConnection);
        LogManagementUtils.showLog(TAG+context.toString() + "已解除 WebSocketService 绑定");
        webSocketServiceBindSuccess = false;
        if (mWebSocketService != null){
            mWebSocketService.removeListener(mSocketListener);
        }
    }
}
