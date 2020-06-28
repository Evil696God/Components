

package com.kuke.common.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * @date: 2018-12-17
 * @version: 1.0
 * @author: wangchenxing
 */
public class PublicHandler extends Handler {
    private Task task;

    public PublicHandler(Task task) {
        super();
        this.task = task;
    }

    public PublicHandler(Task task, Looper mLooper) {
        super(mLooper);
        this.task = task;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (task != null) {
            task.doSomething(msg);
        }
    }

    public interface Task {
        void doSomething(Message msg);
    }
}
