

package com.kuke.common.base.baserecyclerfragment.recyclerinterface;


/**
 * 列表item长按点击接口
 *
 * @date: 2020-3-25
 * @version: 1.0
 * @author: wangchenxing
 */

public interface RecyclerViewOnItemLongClickListener<DataType> {
    public abstract void onLongClick(DataType dataType);
}
