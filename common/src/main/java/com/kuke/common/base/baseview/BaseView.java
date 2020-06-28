

package com.kuke.common.base.baseview;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kuke.common.value.UiKit;

import androidx.viewbinding.ViewBinding;

import static com.kuke.common.value.StringValue.FULL_WIDTH_SPACE;


/**
 * @date: 2018-12-03
 * @version: 1.0
 * @author: wangchenxing
 */
public abstract class BaseView<V extends ViewBinding> extends RelativeLayout {
    protected Activity context;
    protected V viewBinding;
    protected LayoutInflater layoutInflater;

    public BaseView(Context context) {
        super(context);
        initializeAttributeSet(context);
        initView();
    }

    public BaseView(
            Context context,
            AttributeSet attrs
    ) {
        super(
                context,
                attrs
        );
        initializeAttributeSet(context);
        initView();
    }

    public BaseView(
            Context context,
            AttributeSet attrs,
            int defStyleAttr
    ) {
        super(
                context,
                attrs,
                defStyleAttr
        );
        initializeAttributeSet(context);
        initView();
    }

    protected abstract V getViewBinding();

    protected abstract void initView();

    protected void setTheLastBitToRed(
            TextView textView,
            String content
    ) {
        SpannableString spannableString = new SpannableString(content);
        int length = spannableString.length();
        spannableString.setSpan(
                new ForegroundColorSpan(UiKit.RED),
                length - 1,
                length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        textView.setText(spannableString);
    }

    protected String removeSpaces(String text) {
        String trimText = text.trim();
        if (trimText.startsWith(FULL_WIDTH_SPACE)) {
            trimText = trimText.substring(
                    1,
                    trimText.length()
            ).trim();
        }
        if (trimText.endsWith(FULL_WIDTH_SPACE)) {
            trimText = trimText.substring(
                    0,
                    trimText.length() - 1
            ).trim();
        }
        return trimText;
    }

    private void initializeAttributeSet(Context context) {
        this.context = (Activity) context;
        layoutInflater = LayoutInflater.from(context);
        viewBinding = getViewBinding();
        if (viewBinding != null) {
            addView(viewBinding.getRoot());
        }
    }
}
