package com.kuke.sheet_music.module.search_music_list.view;

import com.kuke.common.base.baserecyclerfragment.view.BaseRecyclerAdapter;
import com.kuke.common.base.baserecyclerfragment.view.BaseRecyclerFragment;
import com.kuke.common.utils.LogManagementUtils;
import com.kuke.common.value.ElementTag;
import com.kuke.sheet_music.module.search_music_list.model.SearchMusicListBean;
import com.kuke.sheet_music.module.search_music_list.presenter.SearchMusicListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索曲谱列表界面
 *
 * @date: 2020-05-11
 * @version: 1.0
 * @author: wangchenxing
 */
public class SearchMusicListFragment extends BaseRecyclerFragment<SearchMusicListPresenter> {
    public SearchMusicListFragment() {
        setFragmentTag(ElementTag.SEARCH_MUSIC_LIST);
    }

    @Override
    public void initializeData() {
        setDataList();
    }

    @Override
    public BaseRecyclerAdapter setRecyclerViewAdapter(List asyncData) {
        SearchMusicListAdapter searchListAdapter = new SearchMusicListAdapter();
        searchListAdapter.setDataSet(asyncData);
        recyclerView.setAdapter(searchListAdapter);
        return searchListAdapter;
    }

    public void setDataList() {
        ArrayList<SearchMusicListBean> arrayList = new ArrayList();
        for (int i = 0; i < 15; i++) {
            arrayList.add(new SearchMusicListBean());
        }
        presenter.setDataList(arrayList);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogManagementUtils.Companion.showLog("SearchListFragment--hidden:" + hidden);
    }
}
