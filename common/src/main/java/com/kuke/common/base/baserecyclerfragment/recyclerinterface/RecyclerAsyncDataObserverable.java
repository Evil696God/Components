

package com.kuke.common.base.baserecyclerfragment.recyclerinterface;

import java.util.List;

/**
 * 被观察者接口
 *
 * @date: 2020-3-25
 * @version: 1.0
 * @author: wangchenxing
 */
public interface RecyclerAsyncDataObserverable<T> {
    void synchronizeData(List<T> asyncData);

    void addData(List<T> asyncData);

    void notifyObserver();
}
