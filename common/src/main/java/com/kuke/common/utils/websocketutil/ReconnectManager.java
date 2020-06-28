

package com.kuke.common.utils.websocketutil;

import android.os.Handler;


import com.kuke.common.utils.LogManagementUtils;

import org.java_websocket.client.WebSocketClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author John.Gu
 * @data 2019/1/30
 * @email guqinghong@beyondsoft.com
 * @remark 负责 WebSocket 重连，
 */
public class ReconnectManager {

    private static final String TAG = "WebSocket:";

    private WebSocketThread mWebSocketThread;

    /**
     * 是否正在重连
     */
    private volatile boolean retrying;
    private volatile boolean destroyed;
    private final ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

    ReconnectManager(WebSocketThread mWebSocketThread) {
        this.mWebSocketThread = mWebSocketThread;
        retrying = false;
        destroyed = false;
    }

    /**
     * 开始重新连接，连接方式为每个500ms连接一次，持续十五次。
     */
    synchronized void performReconnect() {
        if (retrying) {
            LogManagementUtils.showLog(TAG+"正在重连，请勿重复调用。");
        } else {
            retry();
        }
    }

    /**
     * 开始重连
     */
    private synchronized void retry() {
        try {
            if (!retrying) {
                retrying = true;
                synchronized (singleThreadPool) {
                    singleThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            retrying = true;
                            while (retrying) {
                                if (destroyed) {
                                    retrying = false;
                                    return;
                                }
                                Handler handler = mWebSocketThread.getHandler();
                                WebSocketClient websocket = mWebSocketThread.getSocket();
                                if (handler != null && websocket != null) {
                                    if (mWebSocketThread.getConnectState() == 2) {
                                        break;
                                    } else if (mWebSocketThread.getConnectState() == 1) {
                                        continue;
                                    } else {
                                        handler.sendEmptyMessage(MessageType.CONNECT);
                                    }
                                } else {
                                    break;
                                }
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    LogManagementUtils.showLog(TAG+ "retry()");
                                    if (destroyed = true) {
                                        retrying = false;
                                        return;
                                    } else {
                                        Thread.currentThread().interrupt();
                                    }
                                }
                            }
                            retrying = false;
                        }
                    });
                }
            }
        }catch (Exception e){
            LogManagementUtils.showLog(e.toString());
        }
    }

    /**
     * 销毁资源，并停止重连
     */
    void destroy() {
        destroyed = true;
        if (singleThreadPool != null) {
            singleThreadPool.shutdownNow();
        }
        mWebSocketThread = null;
    }
}
