package com.kuke.common.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * 图片加载工具类
 *
 * @date: 2020-04-02
 * @version: 1.0
 * @author: wangchenxing
 */
public class ImageLoadingUtils {
    public static void loadImageToImageView(
            Context context,
            String url,
            ImageView imageView,
            int width,
            int height
    ) {
        if (width == 0) {
            Glide.with(context).load(url).into(imageView);
        } else {
            Glide.with(context).load(url).override(width, height).into(imageView);
        }
    }

    public static Disposable downloadImageToLocal(
            Context context,
            String url,
            LocalThreadManagementUtils.UIThread uiThread,
            LocalThreadManagementUtils.CustomizedThrowable customizedThrowable
    ) {
        return Observable.create((ObservableOnSubscribe<String>) emitter -> {
            downloadImage(context, url, emitter);
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        content -> {
                            uiThread.doThing(content);
                        },
                        throwable -> {
                            customizedThrowable.doThing(throwable);
                        }
                );
    }

    /**
     * 下载图片(需要异步执行)
     *
     * @param context
     * @param url
     * @param emitter
     */
    private static void downloadImage(
            Context context,
            String url,
            ObservableEmitter<String> emitter
    ) {
        Glide.with(context).downloadOnly().load(url).addListener(new RequestListener<File>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
                emitter.onError(new Throwable());
                return false;
            }

            @Override
            public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
                File tempFileDirectory = new File(FileUtils.getStorageImageDirectory(context));
                if (!tempFileDirectory.exists()) {
                    tempFileDirectory.mkdir();
                }
                File file = new File(FileUtils.getStorageImageDirectory(context), url);
                if (FileUtils.copy(resource, file)) {
                    emitter.onNext(url);
                } else {
                    emitter.onError(new Throwable());
                }
                return false;
            }
        });
    }
}
