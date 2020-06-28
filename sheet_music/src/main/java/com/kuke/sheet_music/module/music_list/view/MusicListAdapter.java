package com.kuke.sheet_music.module.music_list.view;

import android.content.Context;

import com.kuke.common.base.baserecyclerfragment.view.BaseRecyclerAdapter;
import com.kuke.sheet_music.databinding.ItemViewMusicListBinding;
import com.kuke.sheet_music.module.music_list.model.MusicListBean;

import java.util.List;
/**
 * 曲谱列表适配器
 *
 * @date: 2020-05-11
 * @version: 1.0
 * @author: wangchenxing
 */
public class MusicListAdapter extends BaseRecyclerAdapter<MusicListBean, MusicListItemView, ItemViewMusicListBinding> {
    @Override
    public MusicListItemView getItemView(Context context) {
        MusicListItemView musicListItemView = new MusicListItemView(context);
        return musicListItemView;
    }

    @Override
    public void setDataSet(List<MusicListBean> dataSet) {
        this.dataSet = dataSet;
    }
}
