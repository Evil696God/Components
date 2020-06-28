package com.kuke.sheet_music.module.search_music_list.view;

import android.content.Context;

import com.kuke.common.base.baserecyclerfragment.view.BaseItemView;
import com.kuke.sheet_music.databinding.ItemViewSearchListBinding;
import com.kuke.sheet_music.module.search_music_list.model.SearchMusicListBean;

/**
 * 搜索曲谱列表ItemView
 *
 * @date: 2020-05-11
 * @version: 1.0
 * @author: wangchenxing
 */
public class SearchMusicListItemView extends BaseItemView<SearchMusicListBean, ItemViewSearchListBinding> {
    public SearchMusicListItemView(Context context) {
        super(context);
    }

    @Override
    public void bindView(SearchMusicListBean data, int position) {

    }

    @Override
    protected ItemViewSearchListBinding getViewBinding() {
        return ItemViewSearchListBinding.inflate(layoutInflater);
    }

    @Override
    protected void initView() {

    }
}
