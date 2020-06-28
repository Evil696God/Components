

package com.kuke.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @date: 2018-01-14
 * @version: 1.0
 * @author: wangchenxing
 */
public class DownloadManager implements Runnable {
    private String downloadUrl;
    private String storePath;
    DownloadListener downloadListener;
    File storeFile;
    float length;

    public DownloadManager(
            String downloadUrl,
            String storePath,
            DownloadListener downloadListener
    ) {
        this.downloadUrl = downloadUrl;
        this.storePath = storePath;
        this.downloadListener = downloadListener;
    }

    @Override
    public void run() {
        float currentLength = 0;
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(downloadUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            // 获取文件大小
            length = connection.getContentLength();
            inputStream = url.openStream();
            storeFile = new File(storePath);
            if (!storeFile.exists()) {
                if (!storeFile.getParentFile().exists()) {
                    storeFile.getParentFile().mkdirs();
                }
                storeFile.createNewFile();
            } else {
                storeFile.delete();
                if (!storeFile.getParentFile().exists()) {
                    storeFile.getParentFile().mkdirs();
                }
                storeFile.createNewFile();
            }
            fileOutputStream = new FileOutputStream(storePath);
            byte[] buffer = new byte[4 * 1024];
            int read = 0;
            if (downloadListener != null) {
                downloadListener.onPrepareDownload(100);
            }
            int progress;
            int indicator = 0;
            while ((read = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(
                        buffer,
                        0,
                        read
                );
                currentLength = currentLength + read;
                progress = (int) (currentLength / length * 100);
                if (progress > indicator) {
                    downloadListener.onDowningListener(
                            progress,
                            100
                    );
                    indicator = indicator + 5;
                }
            }

            downloadListener.onDowningListener(
                    100,
                    100
            );

            if (downloadListener != null) {
                downloadListener.onComplete(
                        100,
                        storeFile
                );
            }
            closeConnect(
                    fileOutputStream,
                    inputStream,
                    connection
            );
        } catch (IOException e) {
            e.printStackTrace();
            if (downloadListener != null) {
                downloadListener.onError(e);
            }
        } finally {
            closeConnect(
                    fileOutputStream,
                    inputStream,
                    connection
            );
        }
    }

    private void closeConnect(
            FileOutputStream fileOutputStream,
            InputStream inputStream,
            HttpURLConnection connection
    ) {
        try {
            if(fileOutputStream != null) {
                fileOutputStream.close();
            }
            if(inputStream != null) {
                inputStream.close();
            }
            if(connection != null) {
                connection.disconnect();
            }

        } catch (IOException e) {
            e.printStackTrace();
            downloadListener.onError(e);
        }
    }
}
