package com.kuke.sheet_music.module.standard_music.view;

import android.os.Bundle;

import com.kuke.common.base.baseactivity.view.BaseActivity;
import com.kuke.sheet_music.databinding.ActivityStandardMusicBinding;
import com.kuke.sheet_music.module.standard_music.presenter.StandardMusicPresenter;

/**
 * 标准曲谱界面
 *
 * @date: 2020-05-07
 * @version: 1.0
 * @author: wangchenxing
 */
public class StandardMusicActivity extends BaseActivity<StandardMusicPresenter, ActivityStandardMusicBinding> {
    @Override
    public ActivityStandardMusicBinding getViewBinding() {
        return ActivityStandardMusicBinding.inflate(layoutInflater);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initializeData() {

    }
}
