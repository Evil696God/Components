

package com.kuke.common.utils;

import android.os.Handler;

/**
 *
 * @date: 2018-12-21
 * @version: 1.0
 * @author: wangchenxing
 */
public class DelayToolUtils {
    public static void timeUpThen(Long delayMillis, final TimeArrives doSomething) {
        new Handler().postDelayed(() -> doSomething.doSomething(), delayMillis);
    }

    public interface TimeArrives {
        public abstract void doSomething();
    }
}
