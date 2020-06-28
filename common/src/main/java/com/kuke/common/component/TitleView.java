package com.kuke.common.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.kuke.common.base.baseview.BaseView;
import com.kuke.common.databinding.TitleViewBinding;

/**
 * 公共标题栏
 *
 * @date: 2020-05-07
 * @version: 1.0
 * @author: wangchenxing
 */
public class TitleView extends BaseView<TitleViewBinding> {
    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected TitleViewBinding getViewBinding() {
        return TitleViewBinding.inflate(layoutInflater);
    }

    @Override
    protected void initView() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        viewBinding.getRoot().setLayoutParams(layoutParams);
    }

    public void setBackButtonOnClickListener(OnClickListener listener) {
        viewBinding.backButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(v);
            }
        });
    }

    public void setSearchButtonOnClickListener(OnClickListener listener) {
        viewBinding.searchButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(v);
            }
        });
    }

    public void setSearchButtonVisible(boolean isVisible) {
        if (isVisible) {
            viewBinding.searchButton.setVisibility(VISIBLE);
        } else {
            viewBinding.searchButton.setVisibility(GONE);
        }
    }

    public void setTitleVisible(boolean isVisible) {
        if (isVisible) {
            viewBinding.title.setVisibility(VISIBLE);
        } else {
            viewBinding.title.setVisibility(GONE);
        }
    }

    public void setInputVisible(boolean isVisible) {
        if (isVisible) {
            viewBinding.inputContent.setVisibility(VISIBLE);
        } else {
            viewBinding.inputContent.setVisibility(GONE);
        }
    }
}
