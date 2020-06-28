

package com.kuke.common.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @author xzk
 * @data 2018/6/7
 * @email o-xiezhengkun@beyondsoft.com
 * @remark
 */
public class KeybordOpera {

    /**
     * 在 fragment 中隐藏软键盘
     *
     * @param v
     */
    public static void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    /**
     * EditText 弹出软键盘
     *
     * @param editText
     */
    public static void showKeyboard(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) editText.getContext().getSystemService(
            Context.INPUT_METHOD_SERVICE);
        if (inputManager.isActive()) {
            inputManager.showSoftInput(editText, 0);
        }
    }
}
