

package com.kuke.common.base.basefragment.view;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kuke.common.base.basefragment.presenter.BaseFragmentPresenter;
import com.kuke.common.utils.LayoutSettingUtils;
import com.kuke.common.utils.MvpInstantiationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.viewbinding.ViewBinding;


/**
 * Fragment基类
 *
 * @date: 2020-04-14
 * @version: 1.0
 * @author: wangchenxing
 */
public abstract class AbstractBaseFragment<V extends ViewBinding> extends Fragment {
    public boolean isInitialized = false;
    protected V viewBinding;
    protected Context context;
    private String title = "";
    private String fragmentTag = "";
    private RelativeLayout total;
    private LayoutInflater layoutInflater;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        layoutInflater = LayoutInflater.from(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState
    ) {
        if (total == null) {
            total = new RelativeLayout(context);
            viewBinding = getViewBinding();
            total.addView(viewBinding.getRoot());

            LayoutSettingUtils.setViewMatch(total);
            initView(savedInstanceState);
        }

        isInitialized = true;
        return total;
    }

    public abstract V getViewBinding();

    public abstract void initView(Bundle savedInstanceState);

    public void startActivityAndFinishThis(Activity activity, String path) {
        ARouter.getInstance().build(path).navigation();
        activity.finish();
    }

    public void startActivityAndFinishThis(
            Activity activity,
            String path,
            Bundle bundle
    ) {
        ARouter.getInstance().build(path).with(bundle).navigation();
        activity.finish();
    }

    public void startActivity(Activity activity, String path) {
        ARouter.getInstance().build(path).navigation();
    }

    public void startActivity(Activity activity, String path, Bundle bundle) {
        ARouter.getInstance().build(path).with(bundle).navigation();
    }

    public String getFragmentTag() {
        return fragmentTag;
    }

    public void setFragmentTag(String fragmentTag) {
        this.fragmentTag = fragmentTag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Context getSupperContext() {
        return context;
    }

    protected boolean checkStringIsNull(String content) {
        return content == null || "".equals(content);
    }

}
