

package com.kuke.common.base.baserecyclerfragment.view;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;


import com.kuke.common.base.baserecyclerfragment.recyclerinterface.RecyclerViewOnItemClickListener;
import com.kuke.common.base.baserecyclerfragment.recyclerinterface.RecyclerViewOnItemLongClickListener;
import com.kuke.common.utils.ViewExtensionUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

/**
 * @date: 2020-3-25
 * @version: 1.0
 * @author: wangchenxing
 */
public abstract class BaseRecyclerAdapter<DataType, ItemView extends BaseItemView<DataType, V>, V extends ViewBinding> extends RecyclerView.Adapter<BaseRecyclerAdapter<DataType, ItemView, V>.ViewHolder> {

    public List<DataType> dataSet = new ArrayList<DataType>();
    protected RecyclerViewOnItemClickListener<DataType> clickListener;
    protected RecyclerViewOnItemLongClickListener<DataType> longClickListener;

    public abstract ItemView getItemView(Context context);

    public abstract void setDataSet(List<DataType> dataSet);

    public DataType getItemData(int layoutPosition) {
        return dataSet.get(layoutPosition);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(getItemView(viewGroup.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        if (viewHolder.itemView != null) {
            final ItemView itemView = (ItemView) viewHolder.itemView;
            final int layoutPosition = viewHolder.getLayoutPosition();
            final DataType dataType = getItemData(layoutPosition);
            itemView.bindView(
                    dataType,
                    layoutPosition
            );
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onClick(position, dataType);
                    }
                    ViewExtensionUtils.preventDuplicateClicks(view);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (longClickListener != null) {
                        longClickListener.onLongClick(dataType);
                        ViewExtensionUtils.preventDuplicateClicks(view);
                        return true;
                    }
                    ViewExtensionUtils.preventDuplicateClicks(view);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setOnItemClickListener(RecyclerViewOnItemClickListener<DataType> listener) {
        this.clickListener = listener;
    }

    public void setOnItemLongClickListener(RecyclerViewOnItemLongClickListener<DataType> listener) {
        this.longClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
