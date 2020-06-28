

package com.kuke.common.value;

import android.content.res.Resources;

import com.kuke.common.utils.ScreenUtils;

/**
 * @date: 2018-12-04
 * @version: 1.0
 * @author: wangchenxing
 */
public class ScreenSize {
    /**
     * 尺寸分类
     */
    public static final int FULL_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static final int FULL_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
    public static final int HALF_FULLWIDTH = Resources.getSystem().getDisplayMetrics().widthPixels / 2;
    public static final int PUBLIC_MARGIN = 40;
    public static final int FOOTVIEW_HEIGHT = ScreenUtils.dp2px(60);
    public static final int FOOTTEXT_SIZE = ScreenUtils.dp2px(10);
    public static final int ONE_LINE_INFO_WITH_ICON_HEIGHT = ScreenUtils.dp2px(60);
    public static final int ONE_LINE_ICON_WITH_INFO_HEIGHT = ScreenUtils.dp2px(40);
    public static final int ONE_LINE_INFO_WITH_CONTENT_HEIGHT = ScreenUtils.dp2px(40);
    public static final int ICON_WIDTH_AND_HEIGHT = ScreenUtils.dp2px(40);
    public static final int PHOTO_HEIGHT = ScreenUtils.dp2px(60);
    public static final int INPUT_CONTENT_WIDTH = ScreenUtils.dp2px(260);
    public static final int INPUT_CONTENT_HEIGHT = ScreenUtils.dp2px(30);
    public static final int TEXTVIEW_AND_EDITTEXT_HEIGHT = ScreenUtils.dp2px(20);
    public static final int PUBLIC_LIST_POPUP_WINDOW_HEIGHT = ScreenUtils.dp2px(45);

    /**
     * 权重
     */
    public static final float FULL_WEIGHT = 1.0f;

    /**
     * 标题模块
     */
    public static final float BUTTON_SIZE = ScreenUtils.sp2px(15);

    /**
     * 公共按键
     */
    public static final int PUBLIC_BUTTON_WIDTH = ScreenUtils.dp2px(90);
    public static final int PUBLIC_BUTTON_HEIGHT = ScreenUtils.dp2px(13);
    public static final float PUBLIC_BUTTON_TEXT_SIZE = ScreenUtils.sp2px(7);

    /**
     * 个人模块
     */
    public static final int FACIAL_IDENTITY_STATUS = ScreenUtils.dp2px(44);
    public static final int MY_PROFILE_WIDTH = ScreenUtils.dp2px(335);
    public static final int MY_PROFILE_HEIGHT = ScreenUtils.dp2px(66);

    /**
     * 登录模块
     */
    public static final int LOGIN_INPUT_WIDTH = ScreenUtils.dp2px(180);
    public static final int LOGIN_INPUT_HEIGHT = ScreenUtils.dp2px(80);
    public static final int UPDATE_VERSION_CONFIRM_WIDTH = ScreenUtils.dp2px(125);
    public static final int UPDATE_VERSION_CONFIRM_HEIGHT = ScreenUtils.dp2px(20);
    public static final int FORGET_PASSWORD_SIZE = ScreenUtils.sp2px(6);
    public static final int LOGIN_INPUT_SIZE = ScreenUtils.sp2px(6);

    /**
     * 访客模块
     */
    public static final int VISITOR_SEARCH_WIDTH = ScreenUtils.dp2px(360);
    public static final int VISITOR_SEARCH_HEIGHT = ScreenUtils.dp2px(56);
    public static final int SEARCH_CONFIRM_WIDTH = ScreenUtils.dp2px(8);
    public static final int SEARCH_CONFIRM_HEIGHT = ScreenUtils.dp2px(8);
    public static final int SEARCH_CONTENT_WIDTH = ScreenUtils.dp2px(253);
    public static final int SEARCH_CONTENT_HEIGHT = ScreenUtils.dp2px(34);
    public static final float PUBLIC_EDIT_CONTENT_TEXT_SIZE = ScreenUtils.sp2px(5);
    public static final float SEARCH_CONFIRM_TEXTSIZE_WIDTH = ScreenUtils.sp2px(12);
    public static final int SEARCH_OVERLAY_SIZE_WIDTH = ScreenUtils.dp2px(335);
    public static final int SEARCH_OVERLAY_SIZE_HEIGHT = ScreenUtils.dp2px(34);
    public static final int PUBLIC_PADDING = ScreenUtils.dp2px(5);
    public static final int PUBLIC_TOP_PADDING = ScreenUtils.dp2px(2);

    public static final int PUBLIC_LOGIN_MARGIN_LEFT = 15;
    public static final int PUBLIC_LOGIN_MARGIN_RIGHT = 15;

    /**
     * 主页模块
     */
    public static final int ASSIGNED_DOS_WIDTH = ScreenUtils.dp2px(335);
    public static final int ASSIGNED_DOS_HEIGHT = ScreenUtils.dp2px(13);
    public static final int ANNOUNCEMENT_TEXT_SIZE = ScreenUtils.sp2px(6);
    public static final int ANNOUNCEMENT_LIST_ITEM_VIEW_WIDTH = ScreenUtils.dp2px(107);
    public static final int ANNOUNCEMENT_LIST_ITEM_VIEW_HEIGHT = ScreenUtils.dp2px(36);

    public static final int ANNOUNCEMENT_LIST_ITEM_VIEW_TITLE_SIZE = ScreenUtils.sp2px(5);
    public static final int ANNOUNCEMENT_LIST_ITEM_VIEW_TIME_SIZE = ScreenUtils.sp2px(4);
    public static final int ANNOUNCEMENT_LIST_ITEM_VIEW_CONTENT_SIZE = ScreenUtils.sp2px(5);
    public static final int ANNOUNCEMENT_LIST_ITEM_VIEW_MARGIN = ScreenUtils.dp2px(4);
    public static final int ASSIGNED_DOS_LIST_ITEM_VIEW_TEXT_SIZE = ScreenUtils.sp2px(5);
    public static final int ASSIGNED_DOS_LIST_ITEM_VIEW_ENTRANCE_SIZE = ScreenUtils.sp2px(4);
    public static final int ASSIGNED_DOS_LIST_ITEM_VIEW_ENTRANCE_WIDTH = ScreenUtils.dp2px(28);
    public static final int ASSIGNED_DOS_LIST_ITEM_VIEW_ENTRANCE_HEIGHT = ScreenUtils.dp2px(10);
    public static final int ASSIGNED_JOB_GREEN_SPACE_RESULT_TOTAL_SIZE = ScreenUtils.sp2px(6);
    public static final int LINE = ScreenUtils.dp2px(1);
    public static final int DOS_TAB_LAYOUT_MARGIN = ScreenUtils.dp2px(9);

    /**
     * 客户信息模块
     */
    public static final int FILTER_BY_TITLE_SIZE = ScreenUtils.sp2px(5);
    public static final int SEARCH_VIEW_HEIGHT = ScreenUtils.dp2px(13);
    public static final int SEARCH_CONFIRM_PADDING = ScreenUtils.dp2px(2);
    public static final int FILTER_BY_ICON_WIDTH = ScreenUtils.dp2px(5);
    public static final int FILTER_BY_ICON_HEIGHT = ScreenUtils.dp2px(5);
    public static final int FILTER_BY_MARK_HEIGHT = ScreenUtils.dp2px(10);
    public static final int CUSTOMER_DETAIL_BACK = ScreenUtils.sp2px(5);
    public static final int REMARK_HEIGHT = ScreenUtils.dp2px(25);

}
