

package com.kuke.common.language;

import static com.kuke.common.language.Language.CHINESE;
import static com.kuke.common.language.Language.ENGLISH;
import static com.kuke.common.language.Language.language;

/**
 * 网络请求返回值中英文版本
 *
 * @date: 2020-3-26
 * @version: 1.0
 * @author: wangchenxing
 */
public class NetworkRequestReturnInformation {
    /**
     * 网络请求返回值
     */
    public static String getNetworkReturnInfo(String info) {
        switch (info) {
            case "key_quedingtuichudengluma":
                switch (language) {
                    case CHINESE:
                        return "确定退出登录吗？";
                    case ENGLISH:
                        return "are you sure you want to logout?";
                    default:
                        return "are you sure you want to logout?";
                }
            default:
                switch (language) {
                    case CHINESE:
                        return "网络异常!";
                    case ENGLISH:
                        return "Network Error!";
                    default:
                        return "Network Error!";
                }

        }
    }
}
