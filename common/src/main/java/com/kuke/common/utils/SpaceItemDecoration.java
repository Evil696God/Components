
package com.kuke.common.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * recycleview设置item间间距
 *
 * @date: 2019-02-26
 * @version: 1.0
 * @author: wangchenxing
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int topSpace;
    private int leftSpace;
    private int rightSpace;
    private int bottomSpace;

    public SpaceItemDecoration(
            int topSpace,
            int leftSpace,
            int rightSpace,
            int bottomSpace
    ) {
        this.topSpace = topSpace;
        this.leftSpace = leftSpace;
        this.rightSpace = rightSpace;
        this.bottomSpace = bottomSpace;
    }

    @Override
    public void getItemOffsets(
            Rect outRect,
            View view,
            RecyclerView parent,
            RecyclerView.State state
    ) {
        outRect.top = topSpace;
        outRect.left = leftSpace;
        outRect.right = rightSpace;
        outRect.bottom = bottomSpace;
    }
}