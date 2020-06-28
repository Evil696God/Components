package com.kuke.common.utils;

import com.kuke.common.application.KuKeyApplication;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 线程回调工具类
 *
 * @date: 2018-12-11
 * @version: 1.0
 * @author: wangchenxing
 * @rewriteDate 2019-08-05
 * @rewriter wangchenxing
 * description: 调整可自定义throwable的processData重载方法,自动化同步子线程与主线程返回值和参数类型;
 * 调整返回参数标记变量和自定义异常命名;
 * 添加processData方法的返回值Disposable用于处理重度子线程使用时方便回收。
 */
public class LocalThreadManagementUtils {
    private static final Integer NULL_FLAG = -999999;

    public static <T> Disposable processData(
            final ChildThread<T> childThread,
            final UIThread<T> uiThread
    ) {
        return Observable.create(emitter -> {
            LogManagementUtils.Companion.cleanupLog(KuKeyApplication.getInstance());
            if (childThread != null) {
                try {
                    T t = childThread.doThing();
                    if (t == null) {
                        emitter.onNext(NULL_FLAG);
                    } else {
                        emitter.onNext(t);
                        emitter.onComplete();
                    }
                } catch (Throwable throwable) {
                    emitter.onError(throwable);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        t -> {
                            if (uiThread != null) {
                                if (t instanceof Integer && t.equals(NULL_FLAG)) {
                                    uiThread.doThing(null);
                                } else {
                                    uiThread.doThing((T) t);
                                }
                            }
                        },
                        throwable -> {
                            LogManagementUtils.Companion.e("throwable:" + throwable.toString());
                        }
                );

    }

    /**
     * 自定义throwable处理时,需在ChildThread中使用throw抛出自定义异常。
     */
    public static <T> Disposable processData(
            final ChildThread<T> childThread,
            final UIThread<T> uiThread,
            final CustomizedThrowable customizedThrowable
    ) {
        return Observable.create(emitter -> {
            LogManagementUtils.Companion.cleanupLog(KuKeyApplication.getInstance());
            if (childThread != null) {
                try {
                    T t = childThread.doThing();
                    if (t == null) {
                        emitter.onNext(NULL_FLAG);
                    } else {
                        emitter.onNext(t);
                        emitter.onComplete();
                    }
                } catch (Throwable throwable) {
                    emitter.onError(throwable);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        t -> {
                            if (uiThread != null) {
                                if (t instanceof Integer && t.equals(NULL_FLAG)) {
                                    uiThread.doThing(null);
                                } else {
                                    uiThread.doThing((T) t);
                                }
                            }
                        },
                        (Consumer<? super Throwable>) throwable -> {
                            LogManagementUtils.Companion.e("throwable:" + throwable.toString());
                            if (customizedThrowable != null) {
                                customizedThrowable.doThing(throwable);
                            }
                        }
                );

    }

    public interface ChildThread<T> {
        T doThing() throws Throwable;
    }

    public interface UIThread<T> {
        void doThing(T object);
    }

    public interface CustomizedThrowable {
        void doThing(Throwable throwable);
    }
}