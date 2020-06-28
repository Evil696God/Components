package com.kuke.common.utils.language;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;

import com.kuke.common.language.Language;
import com.kuke.common.utils.SharedPrefrenceUtils;

import java.util.Locale;

/**
 * @author xzk
 * @data 2019/3/20
 * @email xiezhengkun@beyondsoft.com
 * @remark
 */
public class LanContextWrapper extends ContextWrapper {

    public static final String LANG_HK = "hk";
    public static final String LANG_CN = "cn";
    public static final String LANG_EN = "en";

    public LanContextWrapper(Context ctx) {
        super(ctx);
    }

    public static ContextWrapper wrap(Context context) {
        Locale newLocale;
        String lanTag = SharedPrefrenceUtils.getString(context,
                    Language.languageKey, Language.ENGLISH);;
        switch (lanTag) {
            case "def"://没有手动设置过对应的语言则默认读取手机系统的语言
                String locale = Locale.getDefault().getLanguage();
                if (TextUtils.isEmpty(locale)) {
                    newLocale = Locale.CHINESE;
                } else if (locale.contains("en")) {
                    newLocale = Locale.ENGLISH;
                }else if (locale.startsWith("zh")) {
                    String region= Locale.getDefault().getDisplayCountry();
                    if(region.equals("香港特別行政區")||region.equals("台灣")){
                        newLocale= Locale.TRADITIONAL_CHINESE;
                    }else{
                        newLocale= Locale.SIMPLIFIED_CHINESE;
                    }
                }
                else {
                    newLocale = Locale.CHINESE;
                }
                break;
            case LANG_EN://设置为英语
                newLocale = Locale.ENGLISH;
                break;
            case LANG_HK://设置为繁體
                newLocale = Locale.TRADITIONAL_CHINESE;
                break;
            case LANG_CN:
                newLocale= Locale.SIMPLIFIED_CHINESE;
                break;
            default://默认为汉语
                newLocale = Locale.SIMPLIFIED_CHINESE;
                break;
        }
        context = getLanContext(context, newLocale);
        return new ContextWrapper(context);
    }

    /**
     * 初始化Context
     * @param context
     * @param pNewLocale
     * @return
     */
    private static Context getLanContext(Context context, Locale pNewLocale) {
        Resources res = context.getApplicationContext().getResources();//1、获取Resources
        Configuration configuration = res.getConfiguration();//2、获取Configuration
        //3、设置Locale并初始化Context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(pNewLocale);
            LocaleList localeList = new LocaleList(pNewLocale);
            LocaleList.setDefault(localeList);
            configuration.setLocales(localeList);
            context = context.createConfigurationContext(configuration);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            configuration.setLocale(pNewLocale);
            context = context.createConfigurationContext(configuration);
        }
        return context;
    }


    /**
     * 获取手机设置的语言国家
     * @param context
     * @return
     */
    public static String getCountry(Context context) {
        String country;
        Resources resources = context.getApplicationContext().getResources();
        //在7.0以上和7.0一下获取国家的方式有点不一样
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            country = resources.getConfiguration().getLocales().get(0).getCountry();
        } else {
            country = resources.getConfiguration().locale.getCountry();
        }
        return country;
    }
}