

package com.kuke.common.base.baserecyclerfragment.view;

import android.content.Context;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.kuke.common.base.baserecyclerfragment.recyclerinterface.FlowLayoutManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


/**
 * @date: 2020-3-25
 * @version: 1.0
 * @author: wangchenxing
 */
public class BaseNestedRecyclerView extends RecyclerView {
    protected Context context;

    {
        setLayoutManager(new FlowLayoutManager());

        setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));

        ItemAnimator itemAnimator = getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.setChangeDuration(0);
        }
    }

    public BaseNestedRecyclerView(@NonNull Context context) {
        super(context);
    }

    public BaseNestedRecyclerView(
            @NonNull Context context,
            @Nullable AttributeSet attrs
    ) {
        super(
                context,
                attrs
        );
        this.context = context;
    }

    public BaseNestedRecyclerView(
            @NonNull Context context,
            @Nullable AttributeSet attrs,
            int defStyle
    ) {
        super(
                context,
                attrs,
                defStyle
        );
        this.context = context;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && this.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
            this.stopScroll();
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        FlowLayoutManager layoutManager = (FlowLayoutManager) getLayoutManager();
        int widthMode = View.MeasureSpec.getMode(widthSpec);
        int measureWidth = View.MeasureSpec.getSize(widthSpec);
        int heightMode = View.MeasureSpec.getMode(heightSpec);
        int measureHeight = View.MeasureSpec.getSize(heightSpec);
        int width, height;
        if (widthMode == View.MeasureSpec.EXACTLY) {
            width = measureWidth;
        } else {
            //以实际屏宽为标准
            width = getContext().getResources().getDisplayMetrics().widthPixels;
        }
        if (heightMode == View.MeasureSpec.EXACTLY) {
            height = measureHeight;
        } else {
            height = layoutManager.getTotalHeight() + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(width, height);
    }
}
