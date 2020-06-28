package com.kuke.sheet_music.module.music_list.presenter;

import com.kuke.common.base.baserecyclerfragment.presenter.BaseRecyclerPresenter;
import com.kuke.sheet_music.module.music_list.model.MusicListBean;
import com.kuke.sheet_music.module.music_list.model.MusicListModel;
import com.kuke.sheet_music.module.music_list.view.MusicListFragment;

import java.util.ArrayList;

/**
 * @date: 2020-05-11
 * @version: 1.0
 * @author: wangchenxing
 */
public class MusicListPresenter extends BaseRecyclerPresenter<MusicListModel, MusicListFragment> {
    public void setDataList(ArrayList<MusicListBean> arrayList) {
        observerableRecyclerData.addData(arrayList);
    }
}
