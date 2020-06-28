

package com.kuke.common.utils;

import java.io.File;

/**
 *
 * @date: 2018-01-14
 * @version: 1.0
 * @author: wangchenxing
 */
public interface DownloadListener {
    void onPrepareDownload(int total);
    void onDowningListener(
            int current,
            int total
    );
    void onError(Exception e);
    void onComplete(
            int total,
            File file
    );
}
