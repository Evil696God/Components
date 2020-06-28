package com.kuke.sheet_music.module.music_list.view;

import android.content.Context;

import com.kuke.common.base.baserecyclerfragment.view.BaseItemView;
import com.kuke.common.utils.LayoutSettingUtils;
import com.kuke.common.utils.ScreenUtils;
import com.kuke.common.value.ScreenSize;
import com.kuke.sheet_music.databinding.ItemViewMusicListBinding;
import com.kuke.sheet_music.module.music_list.model.MusicListBean;

/**
 * 曲谱列表ItemView
 *
 * @date: 2020-05-11
 * @version: 1.0
 * @author: wangchenxing
 */
public class MusicListItemView extends BaseItemView<MusicListBean, ItemViewMusicListBinding> {
    public MusicListItemView(Context context) {
        super(context);
    }

    @Override
    public void bindView(MusicListBean data, int position) {

    }

    @Override
    protected ItemViewMusicListBinding getViewBinding() {
        return ItemViewMusicListBinding.inflate(layoutInflater);
    }

    @Override
    protected void initView() {
//        LayoutSettingUtils.setViewWidthAndHigh(
//                viewBinding.getRoot(),
//                ScreenUtils.dp2px(150),
//                ScreenUtils.dp2px(150)
//        );
    }
}
