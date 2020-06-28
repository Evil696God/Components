

package com.kuke.common.value;

/**
 * @date: 2018-01-21
 * @version: 1.0
 * @author: wangchenxing
 */
public class StringValue {
    /**
     * 邮箱正则
     */
    public static final String EMAIL_REGULAR = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
    /**
     * 税号正则
     */
    public static final String TAX_RATE_REGULAR = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{2,}$";

    /**
     * 全角空格
     */
    public static final String FULL_WIDTH_SPACE = "　";

    /**
     * 现金
     */
    public static final String CASH = "key_xianjin";
    /**
     * 支票
     */
    public static final String CHECK = "key_zhipiao";
    /**
     * 赊账
     */
    public static final String CREDIT = "key_shezhang";
    /**
     * 街账
     */
    public static final String BILL_TO_BILL = "key_jiezhang";

    /**
     * 货币标识
     */
    public static final String DOLLAR = "$";

    /**
     * 小数点后两位
     */
    public static final String TWO_DECIMAL_PLACES = "%.2f";

    /**
     * 0
     */
    public static final String ZERO = "0";

    /**
     * 1
     */
    public static final String ONE = "1";

    /**
     * 2
     */
    public static final String TWO = "2";

    /**
     * 税率
     */
    public static final String TAX_RATE = "TAX_RATE";

    /**
     * 是否包含税率
     */
    public static final String GST_MODE = "GST_MODE";

    /**
     * 退货接口返回标识
     */
    public static final String RETURN_STOCK = "returnStock";
}
