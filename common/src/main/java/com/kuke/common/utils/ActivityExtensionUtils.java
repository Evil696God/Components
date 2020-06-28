

package com.kuke.common.utils;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * Activity相关扩展方法
 *
 * @date: 2018-12-03
 * @version: 1.0
 * @author: wangchenxing
 */
public class ActivityExtensionUtils {
    public static void addFragment(
            AppCompatActivity activity,
            int containerID,
            Fragment fragment,
            String fragmentTag,
            boolean isHide
    ) {
        if (activity != null) {
            FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(
                    containerID,
                    fragment,
                    fragmentTag
            );
            if (isHide) {
                fragmentTransaction.hide(fragment);
            }
            fragmentTransaction.commitAllowingStateLoss();
        }
    }
}
