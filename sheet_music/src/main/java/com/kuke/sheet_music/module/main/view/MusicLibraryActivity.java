package com.kuke.sheet_music.module.main.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.kuke.common.base.baseactivity.view.BaseActivity;
import com.kuke.common.utils.ActivityExtensionUtils;
import com.kuke.common.utils.PublicHandler;
import com.kuke.sheet_music.R;
import com.kuke.sheet_music.databinding.ActivityMusicLibraryBinding;
import com.kuke.sheet_music.module.main.presenter.MusicLibraryPresenter;
import com.kuke.sheet_music.module.music_list.view.MusicListFragment;

/**
 * 曲谱库界面
 *
 * @date: 2020-05-07
 * @version: 1.0
 * @author: wangchenxing
 */
public class MusicLibraryActivity extends BaseActivity<MusicLibraryPresenter, ActivityMusicLibraryBinding> {
    private Handler handler = new PublicHandler(new PublicHandler.Task() {
        @Override
        public void doSomething(Message msg) {

        }
    });

    @Override
    public ActivityMusicLibraryBinding getViewBinding() {
        return ActivityMusicLibraryBinding.inflate(layoutInflater);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        MusicListFragment musicListFragment = new MusicListFragment();
        ActivityExtensionUtils.addFragment(
                this,
                R.id.fullContent,
                musicListFragment,
                musicListFragment.getFragmentTag(),
                false
        );
    }

    @Override
    public void initializeData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
