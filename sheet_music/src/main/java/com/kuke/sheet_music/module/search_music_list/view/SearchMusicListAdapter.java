package com.kuke.sheet_music.module.search_music_list.view;

import android.content.Context;

import com.kuke.common.base.baserecyclerfragment.view.BaseRecyclerAdapter;
import com.kuke.sheet_music.databinding.ItemViewSearchListBinding;
import com.kuke.sheet_music.module.search_music_list.model.SearchMusicListBean;

import java.util.List;

/**
 * 搜索曲谱列表适配器
 *
 * @date: 2020-05-11
 * @version: 1.0
 * @author: wangchenxing
 */
public class SearchMusicListAdapter extends BaseRecyclerAdapter<SearchMusicListBean, SearchMusicListItemView, ItemViewSearchListBinding> {
    @Override
    public SearchMusicListItemView getItemView(Context context) {
        SearchMusicListItemView searchMusicListItemView = new SearchMusicListItemView(context);
        return searchMusicListItemView;
    }

    @Override
    public void setDataSet(List<SearchMusicListBean> dataSet) {
        this.dataSet = dataSet;
    }
}
