

package com.kuke.common.base.baserecyclerfragment.recyclerinterface;

/**
 * 列表item点击接口
 *
 * @date: 2020-3-25
 * @version: 1.0
 * @author: wangchenxing
 */
public interface RecyclerViewOnItemClickListener<DataType> {
    public abstract void onClick(int position, DataType dataType);
}


