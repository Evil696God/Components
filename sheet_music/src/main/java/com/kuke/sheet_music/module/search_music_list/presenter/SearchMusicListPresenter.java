package com.kuke.sheet_music.module.search_music_list.presenter;

import com.kuke.common.base.baserecyclerfragment.presenter.BaseRecyclerPresenter;
import com.kuke.sheet_music.module.search_music_list.model.SearchMusicListBean;
import com.kuke.sheet_music.module.search_music_list.model.SearchMusicListModel;
import com.kuke.sheet_music.module.search_music_list.view.SearchMusicListFragment;

import java.util.ArrayList;

/**
 * @date: 2020-05-11
 * @version: 1.0
 * @author: wangchenxing
 */
public class SearchMusicListPresenter extends BaseRecyclerPresenter<SearchMusicListModel, SearchMusicListFragment> {
    public void setDataList(ArrayList<SearchMusicListBean> arrayList) {
        observerableRecyclerData.addData(arrayList);
    }
}
