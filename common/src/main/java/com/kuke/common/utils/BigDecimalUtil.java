package com.kuke.common.utils;

import java.math.BigDecimal;

/**
 * 大数，价格工具类
 *
 * @date: 2020-3-26
 * @version: 1.0
 * @author: wangchenxing
 */
public class BigDecimalUtil {
    private static final int decimalPoint = 2;

    /**
     * 金额保留两位小数
     */
    public static String moneyUp2Last(BigDecimal price) {
        BigDecimal decimal = price.setScale(decimalPoint, BigDecimal.ROUND_UP);
        return "$" + decimal.toString();
    }

    /**
     * 金额保留两位小数
     */
    public static String moneyUp2LastNoMoney(BigDecimal price) {
        BigDecimal decimal = price.setScale(decimalPoint, BigDecimal.ROUND_UP);
        return decimal.toString();
    }

    /**
     * 小数保留一位小数
     */
    public static String moneyUp1Last(BigDecimal price) {
        BigDecimal decimal = price.setScale(1, BigDecimal.ROUND_UP);
        return decimal.toString();
    }

    /**
     * 字符串转化为BigDecimal
     *
     * @param content
     * @return
     */
    public static BigDecimal stringToBigDecimal(String content) {
        if (content == null || content.equals("")) {
            content = "0.0";
        }
        if (content.startsWith(".")) {
            content = 0 + content;
        }
        BigDecimal bigDecimal = new BigDecimal(content);
        bigDecimal = bigDecimal.setScale(
                decimalPoint,
                BigDecimal.ROUND_HALF_UP
        );
        return bigDecimal;
    }
}
