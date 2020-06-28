

package com.kuke.common.base.baserecyclerfragment.recyclerinterface;


import com.kuke.common.base.basebean.BaseBean;
import com.kuke.common.base.baserecyclerfragment.recyclerinterface.RecyclerAsyncDataObserverable;
import com.kuke.common.base.baserecyclerfragment.recyclerinterface.RecyclerViewNotifyListener;

import java.util.ArrayList;
import java.util.List;


/**
 * 列表数据更改被观察者
 *
 * @date: 2020-3-25
 * @version: 1.0
 * @author: wangchenxing
 */
public class ObserverableRecyclerData implements RecyclerAsyncDataObserverable {

    private List asyncData = new ArrayList();
    private RecyclerViewNotifyListener recyclerViewNotifyListener;

    public ObserverableRecyclerData(RecyclerViewNotifyListener recyclerViewNotifyListener) {
        this.recyclerViewNotifyListener = recyclerViewNotifyListener;
    }

    @Override
    public void synchronizeData(List asyncData) {
        this.asyncData = asyncData;
        notifyObserver();
    }

    @Override
    public void addData(List asyncData) {
        this.asyncData.addAll(asyncData);
        notifyObserver();
    }

    public void removeData(BaseBean dataBean) {
        asyncData.remove(dataBean);
        notifyObserver();
    }

    public List<Object> getAsyncData() {
        return asyncData;
    }

    @Override
    public void notifyObserver() {
        recyclerViewNotifyListener.notifyUI();
    }

    public void setRecyclerViewNotifyListener(RecyclerViewNotifyListener recyclerViewNotifyListener) {
        this.recyclerViewNotifyListener = recyclerViewNotifyListener;
    }
}
