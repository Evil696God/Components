

package com.kuke.common.base.baserecyclerfragment.view;

import android.app.Activity;
import android.content.Context;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * @date: 2020-3-25
 * @version: 1.0
 * @author: wangchenxing
 */
public class BaseRecyclerView extends RecyclerView {
    private Context context;
    private RecyclerView.OnScrollListener pullDownOrUpRefreshData = new OnScrollListener() {
        boolean isUp = false;
        boolean isPullDown = false;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            int lastPosition = -1;
            int firstPosition = -1;
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof GridLayoutManager) {
                    // 通过LayoutManager找到当前显示的最后的item的position
                    lastPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                    firstPosition = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
                } else if (layoutManager instanceof LinearLayoutManager) {
                    lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    firstPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    // 因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，所以这里取到的是一个数组
                    // 得到这个数组后再取到数组中position值最大的那个就是最后显示的position值了
                    int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                    ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(lastPositions);
                    lastPosition = findMax(lastPositions);
                }

                // 判断界面显示的最后item的position是否等于itemCount总数-1也就是最后一个item的position
                // 如果相等则说明已经滑动到最后了
                int num = recyclerView.getLayoutManager().getItemCount();
                if (lastPosition == num - 1 && isUp) {
                    Activity activity = null;
                    if (context instanceof Activity) {
                        activity = (Activity) BaseRecyclerView.this.context;

                    } else if (context instanceof ContextWrapper) {
                        activity = (Activity) ((ContextWrapper) context).getBaseContext();
                    }
                    if (activity != null) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loadData();
                            }
                        });
                    }
                } else if (firstPosition == 0 && isPullDown) {
                    Activity activity = null;
                    if (context instanceof Activity) {
                        activity = (Activity) BaseRecyclerView.this.context;

                    } else if (context instanceof ContextWrapper) {
                        activity = (Activity) ((ContextWrapper) context).getBaseContext();
                    }
                    if (activity != null) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                refresh();
                            }
                        });
                    }
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy > 0) {
                isUp = true;
                isPullDown = false;
            } else {
                isUp = false;
                isPullDown = true;
            }
        }

    };

    {
        setLayoutManager(new LinearLayoutManager(
                context,
                VERTICAL,
                false
        ));
        setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));

        ItemAnimator itemAnimator = getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.setChangeDuration(0);
        }

        addOnScrollListener(pullDownOrUpRefreshData);
    }

    public BaseRecyclerView(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public BaseRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public BaseRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && this.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
            this.stopScroll();
        }
        return super.onInterceptTouchEvent(event);
    }

    //找到数组中的最大值
    public int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public void loadData() {
    }

    public void refresh() {
    }
}
