

package com.kuke.common.base.baserecyclerfragment.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.kuke.common.base.baseview.BaseView;

import androidx.viewbinding.ViewBinding;

/**
 * @date: 2020-3-25
 * @version: 1.0
 * @author: wangchenxing
 */
public abstract class BaseItemView<DataType, V extends ViewBinding> extends BaseView<V> {

    public BaseItemView(Context context) {
        super(context);
    }

    public abstract void bindView(DataType data, int position);

    protected void startActivity(
            Class<?> cls,
            Bundle bundle
    ) {
        Activity context = this.context;
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
