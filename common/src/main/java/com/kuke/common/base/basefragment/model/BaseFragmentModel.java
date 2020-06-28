

package com.kuke.common.base.basefragment.model;


import com.kuke.common.base.basefragment.presenter.BaseFragmentPresenter;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

/**
 * MVP框架 Fragment Model基类
 *
 * @date: 2020-03-25
 * @version: 1.0
 * @author: wangchenxing
 */
public class BaseFragmentModel<T extends BaseFragmentPresenter> {
    protected T presenter;
    public ArrayList<Disposable> disposableList;

    public BaseFragmentModel() {
        disposableList = new ArrayList();
    }

    public void setPresenter(T presenter) {
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
