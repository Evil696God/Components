

package com.kuke.common.base.baseactivity.model;

import com.kuke.common.base.baseactivity.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * MVP框架 model基类
 *
 * @date: 2020-03-25
 * @version: 1.0
 * @author: wangchenxing
 */
public class BaseModel<P extends BasePresenter> {
    public ArrayList<Disposable> disposableList;

    public BaseModel() {
        disposableList = new ArrayList();
    }

    protected P presenter;

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
