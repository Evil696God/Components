

package com.kuke.common.base.baserecyclerfragment.model;


import com.kuke.common.base.baserecyclerfragment.presenter.BaseRecyclerPresenter;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

/**
 * @author: wangchenxing
 * @date: 2020-03-25
 * @version: 1.0
 */
public class BaseRecyclerModel<P extends BaseRecyclerPresenter> {
    protected P presenter;
    public ArrayList<Disposable> disposableList;

    public BaseRecyclerModel() {
        disposableList = new ArrayList();
    }

    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    public void onDestroy() {
        if (disposableList != null) {
            for (int i = 0; i < disposableList.size(); i++) {
                Disposable disposable = disposableList.get(i);
                if (disposable != null && !disposable.isDisposed()) {
                    disposable.dispose();
                }
            }
        }
    }
}
