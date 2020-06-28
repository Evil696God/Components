

package com.kuke.common.value;

/**
 *
 * @date: 2018-12-01
 * @version: 1.0
 * @author: wangchenxing
 */
public class ArgumentKey {
    public static final String VISITOR_ITINERARY_DETAILS_BEAN = "visitorItineraryDetailsBean";
    public static final String VISITOT_INFO_BEAN_OF_DATABEAN = "CustomersRecyclerBean.DataBean";
    public static final String ADD = "add";
    public static final String MODIFY = "modify";

    /**
     * 时间选择模块
     */
    public static final int START_TIME_TYPE = 1;
    public static final int END_TIME_TYPE = 2;
    public static final int ENROLLMENT_DATE_TYPE = 3;
    public static final int MAX_HOURES = 24;
    public static final int HALF_OF_MAX_HOURES = 12;
    public static final int MAX_MINUTE = 7;
    public static final int START_YEAR = 1999;
    public static final int END_YEAR = 2020;
    public static final long ONE_HOUR = (60 * 60 * 1000);

    /**
     * sp存储
     */
    public static final String SAVE_PASSWORD = "automaticLogin";

    public static final String DOWNLOAD_URL = "downloadUrl";
    public static final String STORE_APK_PATH = "saveApkPath";

    /**
     * 版本判断
     */
    public static final int CAMERA_BUILD_VERSION = 24;
    public static final int DRAWABLE_BUILD_VERSION = 16;
    public static final int FILE_BUILD_VERSION = 24;

    /**
     * 网络请求模块
     */
    public static final int REQUSET_SUCESS_CODE = 200;
    public static final int REQUEST_ERROR_CODE = 500;
    public static final int REQUEST_EXCEPTION_CODE = 400;
    public static final int REQUSET_ERROR_FIVE_TIMES = 5;
    public static final int PHONE_ALREADT_PRESENCE = 800001;


    /**
     * 厂商权限模块
     */
    public static final String HUAWEI = "huawei";
    public static final String XIAOMI = "xiaomi";
    public static final String OPPO = "oppo";
    public static final String VIVO = "vivo";
    public static final String MEIZU = "meizu";

    /**
     * 校验版本
     */
    public static final String APK = ".apk";

    /**
     * 自定义view模块
     */
    public static final int NUMBER_TWO = 2;
    public static final int NUMBER_THREE = 3;
    public static final int NUMBER_FIFTEEN = 15;
    public static final int NUMBER_SXITEEN = 16;
    public static final int NUMBER_EIGHTEEN = 18;
}
