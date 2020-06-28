
package com.kuke.common.language;


import com.kuke.common.value.MonthValue;

/**
 * 语言中英文版本
 *
 * @date: 2020-3-26
 * @version: 1.0
 * @author: wangchenxing
 */
public class Language {
    //    public static String language = Locale.getDefault().getLanguage();
    public static final String CHINESE = "zh";
    public static final String ENGLISH = "en";
    public static final String languageKey = "language";
    /**
     * 当前语言(现阶段屏蔽系统语言选择,以app内语言选择优先)
     */
    public static String language = "en";

    /**
     * 登录模块
     */
    public static String getPublicButton() {
        switch (language) {
            case CHINESE:
                return "登录";
            case ENGLISH:
                return "Login";
            default:
                return "Login";
        }
    }
}
