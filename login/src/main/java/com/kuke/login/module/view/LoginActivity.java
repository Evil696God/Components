package com.kuke.login.module.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kuke.common.base.baseactivity.view.BaseActivity;
import com.kuke.common.value.RoutePath;
import com.kuke.login.R;
import com.kuke.login.databinding.ActivityLoginBinding;
import com.kuke.login.module.presenter.LoginPresenter;
import com.tencent.mm.opensdk.diffdev.DiffDevOAuthFactory;
import com.tencent.mm.opensdk.diffdev.IDiffDevOAuth;
import com.tencent.mm.opensdk.diffdev.OAuthErrCode;
import com.tencent.mm.opensdk.diffdev.OAuthListener;

/**
 * 登陆界面
 *
 * @date: 2020-05-07
 * @version: 1.0
 * @author: wangchenxing
 */
@Route(path = RoutePath.LOGIN)
public class LoginActivity extends BaseActivity<LoginPresenter, ActivityLoginBinding> implements OAuthListener {

    @Override
    public ActivityLoginBinding getViewBinding() {
        return ActivityLoginBinding.inflate(layoutInflater);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initializeData() {
        IDiffDevOAuth oauth = DiffDevOAuthFactory.getDiffDevOAuth();

    }

    @Override
    public void onAuthGotQrcode(String qrcodeImgPath, byte[] bytes) {
        // 获取二维码图片
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    @Override
    public void onQrcodeScanned() {

    }

    @Override
    public void onAuthFinish(OAuthErrCode oAuthErrCode, String authCode) {

    }
}
