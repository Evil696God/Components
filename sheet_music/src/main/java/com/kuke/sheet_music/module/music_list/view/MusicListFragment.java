package com.kuke.sheet_music.module.music_list.view;

import com.kuke.common.base.baserecyclerfragment.view.BaseRecyclerAdapter;
import com.kuke.common.base.baserecyclerfragment.view.BaseRecyclerFragment;
import com.kuke.common.utils.LogManagementUtils;
import com.kuke.common.value.ElementTag;
import com.kuke.sheet_music.module.music_list.model.MusicListBean;
import com.kuke.sheet_music.module.music_list.presenter.MusicListPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;

/**
 * 曲谱列表界面
 *
 * @date: 2020-05-11
 * @version: 1.0
 * @author: wangchenxing
 */
public class MusicListFragment extends BaseRecyclerFragment<MusicListPresenter> {

    public MusicListFragment() {
        setFragmentTag(ElementTag.MUSIC_LIST);
    }

    @Override
    public void initializeData() {
        setDataList();
    }

    @Override
    public BaseRecyclerAdapter setRecyclerViewAdapter(List asyncData) {
        MusicListAdapter musicListAdapter = new MusicListAdapter();
        musicListAdapter.setDataSet(asyncData);
        recyclerView.setLayoutManager(new GridLayoutManager(
                context,
                6
        ));
        recyclerView.setAdapter(musicListAdapter);
        return musicListAdapter;
    }

    public void setDataList() {
        ArrayList<MusicListBean> arrayList = new ArrayList();
        for (int i = 0; i < 15; i++) {
            arrayList.add(new MusicListBean());
        }
        presenter.setDataList(arrayList);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogManagementUtils.Companion.showLog("MusicListFragment--hidden:" + hidden);
    }
}
